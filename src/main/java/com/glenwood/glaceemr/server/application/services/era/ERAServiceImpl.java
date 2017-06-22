package com.glenwood.glaceemr.server.application.services.era;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.AnsiEobScreens;
import com.glenwood.glaceemr.server.application.models.AnsiEobScreens_;
import com.glenwood.glaceemr.server.application.models.EoberaRefundWriteoff;
import com.glenwood.glaceemr.server.application.models.EoberaRefundWriteoff_;
import com.glenwood.glaceemr.server.application.models.EraFileDetails;
import com.glenwood.glaceemr.server.application.models.EraFileDetails_;
import com.glenwood.glaceemr.server.application.models.HutilEobSd;
import com.glenwood.glaceemr.server.application.models.HutilEobSd_;
import com.glenwood.glaceemr.server.application.models.HutilMasterEob;
import com.glenwood.glaceemr.server.application.models.HutilMasterEob_;
import com.glenwood.glaceemr.server.utils.HUtil;

@Service
public class ERAServiceImpl implements ERAService
{
	
	@Autowired EntityManager entityManager;

	@Override
	public List<ERADetailsBean> getERADetails(String fromDate, String toDate) 
	{
		try
		{
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<ERADetailsBean> criteriaQuery = builder.createQuery(ERADetailsBean.class);
			Root<HutilEobSd> root = criteriaQuery.from(HutilEobSd.class);
			Join<HutilEobSd, HutilMasterEob> eobMasterJoin = root.join(HutilEobSd_.hutilMasterEobJoin, JoinType.INNER);
			Join<HutilEobSd, AnsiEobScreens> eobAnsiJoin = root.join(HutilEobSd_.ansiEobScreensJoin, JoinType.LEFT);
			Join<HutilEobSd, HutilEobSd> eobSdSelfJoin = root.join(HutilEobSd_.eobSdJoin, JoinType.LEFT);
			Join<HutilEobSd, EoberaRefundWriteoff> eobRoundOffJoin = root.join(HutilEobSd_.eobRoundOffJoin, JoinType.LEFT);
			
			Predicate sdSecJoin = builder.equal(eobSdSelfJoin.get(HutilEobSd_.inserver), 1);
			eobSdSelfJoin.on(sdSecJoin);
			
			Expression<Double> wAmount = builder.<Double>selectCase().when(builder.equal(builder.coalesce(eobRoundOffJoin.get(EoberaRefundWriteoff_.eoberaRefundWriteoffType), 0), 1), eobRoundOffJoin.get(EoberaRefundWriteoff_.eoberaRefundWriteoffAmount)).otherwise((double)0);
			Expression<Double> rAmount = builder.<Double>selectCase().when(builder.equal(builder.coalesce(eobRoundOffJoin.get(EoberaRefundWriteoff_.eoberaRefundWriteoffType), 0), 2), eobRoundOffJoin.get(EoberaRefundWriteoff_.eoberaRefundWriteoffAmount)).otherwise((double)0);
	
			criteriaQuery.select(builder.construct(ERADetailsBean.class, eobMasterJoin.get(HutilMasterEob_.id),
					root.get(HutilEobSd_.chequenumber),
					builder.function("to_char", String.class, eobMasterJoin.get(HutilMasterEob_.bpr16), builder.literal("mm/dd/yyyy")),
					eobMasterJoin.get(HutilMasterEob_.prn102),
					eobMasterJoin.get(HutilMasterEob_.bpr02),
					builder.coalesce(builder.diff(builder.sum(builder.coalesce(eobSdSelfJoin.get(HutilEobSd_.payments), 0.00)), builder.coalesce(builder.sum(wAmount), 0.00)), 0.00),
					builder.function("to_char", String.class, root.get(HutilEobSd_.dateOfPosting), builder.literal("mm/dd/yyyy")),
					builder.function("count", Long.class, root.get(HutilEobSd_.dceid)),
					builder.function("count", Long.class, eobSdSelfJoin.get(HutilEobSd_.dceid)),
					builder.sum(builder.<Integer>selectCase().when(builder.equal(builder.coalesce(eobAnsiJoin.get(AnsiEobScreens_.screenGroupId), 0), 1), 1).otherwise(0)),
					builder.sum(builder.<Integer>selectCase().when(builder.equal(builder.coalesce(eobAnsiJoin.get(AnsiEobScreens_.screenGroupId), 0), 3), 1).otherwise(0)),
					eobMasterJoin.get(HutilMasterEob_.sourcefile),
					builder.coalesce(builder.sum(wAmount), 0.00),
					builder.coalesce(builder.sum(rAmount), 0.00),
					builder.diff(builder.coalesce(eobMasterJoin.get(HutilMasterEob_.bpr02), 0.00), builder.coalesce(builder.sum(
							builder.coalesce(builder.sum(eobSdSelfJoin.get(HutilEobSd_.payments)), 0.00), builder.sum(
							builder.coalesce(builder.sum(wAmount), 0.00),
							builder.coalesce(builder.sum(rAmount), 0.00))), 0.00))));
			criteriaQuery.where(builder.isFalse(root.get(HutilEobSd_.checkSettled)),
					builder.isNull(root.get(HutilEobSd_.dateOfPosting)));
			criteriaQuery.groupBy(eobMasterJoin.get(HutilMasterEob_.bpr16),
					root.get(HutilEobSd_.chequenumber),
					eobMasterJoin.get(HutilMasterEob_.sourcefile),
					eobMasterJoin.get(HutilMasterEob_.bpr02),
					eobMasterJoin.get(HutilMasterEob_.id),
					eobMasterJoin.get(HutilMasterEob_.prn102),
					root.get(HutilEobSd_.dateOfPosting),
					wAmount, rAmount);
			criteriaQuery.having(builder.notEqual(builder.function("count", Long.class, root.get(HutilEobSd_.dceid)), builder.function("count", Long.class, eobSdSelfJoin.get(HutilEobSd_.dceid))),
					builder.equal(builder.function("count", Long.class, eobSdSelfJoin.get(HutilEobSd_.dceid)), 0));
			
			List<ERADetailsBean> resultList = entityManager.createQuery(criteriaQuery).getResultList();
			
			for(int beanVar=0; beanVar<resultList.size(); beanVar++)
			{
				if(resultList.get(beanVar).getDecid2Count() == 0)
					resultList.get(beanVar).setExp1("Pending");
				else if(resultList.get(beanVar).getDecid1Count() == resultList.get(beanVar).getDecid2Count())
					resultList.get(beanVar).setExp1("Posted");
				else
					resultList.get(beanVar).setExp1("Partial");
			}
			
			return resultList;
		}
		
		catch(Exception exception)
		{
			exception.printStackTrace();
			return null;
		}
		
		finally
		{
			entityManager.close();
		}
	}
	
	@Override
	public List<ERADetailsBean> getAllPartialERA(String fromDate, String toDate) 
	{
		try
		{
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<ERADetailsBean> criteriaQuery = builder.createQuery(ERADetailsBean.class);
			Root<HutilEobSd> root = criteriaQuery.from(HutilEobSd.class);
			Join<HutilEobSd, HutilMasterEob> eobMasterJoin = root.join(HutilEobSd_.hutilMasterEobJoin, JoinType.INNER);
			Join<HutilEobSd, AnsiEobScreens> eobAnsiJoin = root.join(HutilEobSd_.ansiEobScreensJoin, JoinType.LEFT);
			Join<HutilEobSd, HutilEobSd> eobSdSelfJoin = root.join(HutilEobSd_.eobSdJoin, JoinType.LEFT);
			Join<HutilEobSd, EoberaRefundWriteoff> eobRoundOffJoin = root.join(HutilEobSd_.eobRoundOffJoin, JoinType.LEFT);
			
			Predicate sdSecJoin = builder.equal(eobSdSelfJoin.get(HutilEobSd_.inserver), 1);
			eobSdSelfJoin.on(sdSecJoin);
			
			Expression<Double> wAmount = builder.<Double>selectCase().when(builder.equal(builder.coalesce(eobRoundOffJoin.get(EoberaRefundWriteoff_.eoberaRefundWriteoffType), 0), 1), eobRoundOffJoin.get(EoberaRefundWriteoff_.eoberaRefundWriteoffAmount)).otherwise((double)0);
			Expression<Double> rAmount = builder.<Double>selectCase().when(builder.equal(builder.coalesce(eobRoundOffJoin.get(EoberaRefundWriteoff_.eoberaRefundWriteoffType), 0), 2), eobRoundOffJoin.get(EoberaRefundWriteoff_.eoberaRefundWriteoffAmount)).otherwise((double)0);
	
			criteriaQuery.select(builder.construct(ERADetailsBean.class, eobMasterJoin.get(HutilMasterEob_.id),
					root.get(HutilEobSd_.chequenumber),
					builder.function("to_char", String.class, eobMasterJoin.get(HutilMasterEob_.bpr16), builder.literal("mm/dd/yyyy")),
					eobMasterJoin.get(HutilMasterEob_.prn102),
					eobMasterJoin.get(HutilMasterEob_.bpr02),
					builder.coalesce(builder.diff(builder.sum(builder.coalesce(eobSdSelfJoin.get(HutilEobSd_.payments), 0.00)), builder.coalesce(builder.sum(wAmount), 0.00)), 0.00),
					builder.function("to_char", String.class, root.get(HutilEobSd_.dateOfPosting), builder.literal("mm/dd/yyyy")),
					builder.function("count", Long.class, root.get(HutilEobSd_.dceid)),
					builder.function("count", Long.class, eobSdSelfJoin.get(HutilEobSd_.dceid)),
					builder.sum(builder.<Integer>selectCase().when(builder.equal(builder.coalesce(eobAnsiJoin.get(AnsiEobScreens_.screenGroupId), 0), 1), 1).otherwise(0)),
					builder.sum(builder.<Integer>selectCase().when(builder.equal(builder.coalesce(eobAnsiJoin.get(AnsiEobScreens_.screenGroupId), 0), 3), 1).otherwise(0)),
					eobMasterJoin.get(HutilMasterEob_.sourcefile),
					builder.coalesce(builder.sum(wAmount), 0.00),
					builder.coalesce(builder.sum(rAmount), 0.00),
					builder.diff(builder.coalesce(eobMasterJoin.get(HutilMasterEob_.bpr02), 0.00), builder.coalesce(builder.sum(
							builder.coalesce(builder.sum(eobSdSelfJoin.get(HutilEobSd_.payments)), 0.00), builder.sum(
							builder.coalesce(builder.sum(wAmount), 0.00),
							builder.coalesce(builder.sum(rAmount), 0.00))), 0.00))));
			criteriaQuery.where(builder.isFalse(root.get(HutilEobSd_.checkSettled)));
			criteriaQuery.groupBy(eobMasterJoin.get(HutilMasterEob_.bpr16),
					root.get(HutilEobSd_.chequenumber),
					eobMasterJoin.get(HutilMasterEob_.sourcefile),
					eobMasterJoin.get(HutilMasterEob_.bpr02),
					eobMasterJoin.get(HutilMasterEob_.id),
					eobMasterJoin.get(HutilMasterEob_.prn102),
					root.get(HutilEobSd_.dateOfPosting),
					wAmount, rAmount);
			criteriaQuery.having(builder.notEqual(builder.function("count", Long.class, root.get(HutilEobSd_.dceid)), builder.function("count", Long.class, eobSdSelfJoin.get(HutilEobSd_.dceid))),
					builder.greaterThan(builder.function("count", Long.class, eobSdSelfJoin.get(HutilEobSd_.dceid)), (long) 0));
			
			List<ERADetailsBean> resultList = entityManager.createQuery(criteriaQuery).getResultList();
			
			for(int beanVar=0; beanVar<resultList.size(); beanVar++)
			{
				if(resultList.get(beanVar).getDecid2Count() == 0)
					resultList.get(beanVar).setExp1("Pending");
				else if(resultList.get(beanVar).getDecid1Count() == resultList.get(beanVar).getDecid2Count())
					resultList.get(beanVar).setExp1("Posted");
				else
					resultList.get(beanVar).setExp1("Partial");
			}
			
			return resultList;
		}
		
		catch(Exception exception)
		{
			exception.printStackTrace();
			return null;
		}
		
		finally
		{
			entityManager.close();
		}
	}

	@Override
	public List<ERADetailsBean> getAllPendingERA(String fromDate, String toDate) 
	{
		try
		{
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<ERADetailsBean> criteriaQuery = builder.createQuery(ERADetailsBean.class);
			Root<EraFileDetails> root = criteriaQuery.from(EraFileDetails.class);
			
			criteriaQuery.select(builder.construct(ERADetailsBean.class, root.get(EraFileDetails_.eraFileDetailsCheckno),
					root.get(EraFileDetails_.eraFileDetailsInsName),
					builder.function("to_char", String.class, root.get(EraFileDetails_.eraFileDetailsDownloadedDate), builder.literal("mm/dd/yyyy")),
					builder.function("to_char", String.class, root.get(EraFileDetails_.eraFileDetailsEraDate), builder.literal("mm/dd/yyyy")),
					root.get(EraFileDetails_.eraFileDetailsFilename),
					root.get(EraFileDetails_.eraFileDetailsIsduplicate),
					root.get(EraFileDetails_.eraFileDetailsIsparsed),
					root.get(EraFileDetails_.eraFileDetailsError),
					root.get(EraFileDetails_.eraFileDetailsId)));
			List<ERADetailsBean> resultList = entityManager.createQuery(criteriaQuery).getResultList();
			
			for(int beanVar=0; beanVar<resultList.size(); beanVar++)
			{
				if(resultList.get(beanVar).getIsDuplicate() == 1)
					resultList.get(beanVar).setEraErrorStatus("Duplicate");
				else
				{
					if(resultList.get(beanVar).getIsParsed() == 1)
						resultList.get(beanVar).setEraErrorStatus("Parsed");
					else
					{
						if(HUtil.Nz(resultList.get(beanVar).getEraErrorStatus(), "").equals(""))
							resultList.get(beanVar).setEraErrorStatus("Pending");
					}
				}
					
			}
			
			return resultList;
		}
		
		catch(Exception exception)
		{
			exception.printStackTrace();
			return null;
		}
		
		finally
		{
			entityManager.close();
		}

	}

}