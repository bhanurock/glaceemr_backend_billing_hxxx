package com.glenwood.glaceemr.server.application.specifications;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.Cpt;
import com.glenwood.glaceemr.server.application.models.Cpt_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.H066;
import com.glenwood.glaceemr.server.application.models.H066_;
import com.glenwood.glaceemr.server.application.models.InsCompAddr;
import com.glenwood.glaceemr.server.application.models.InsCompAddr_;
import com.glenwood.glaceemr.server.application.models.InsCompany;
import com.glenwood.glaceemr.server.application.models.NonServiceDetails;
import com.glenwood.glaceemr.server.application.models.NonServiceDetails_;
import com.glenwood.glaceemr.server.application.models.PatientInsDetail;
import com.glenwood.glaceemr.server.application.models.PatientInsDetail_;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.PosTable_;
import com.glenwood.glaceemr.server.application.models.PrimarykeyGenerator;
import com.glenwood.glaceemr.server.application.models.PrimarykeyGenerator_;
import com.glenwood.glaceemr.server.application.models.ReferringDoctor;
import com.glenwood.glaceemr.server.application.models.ReferringDoctor_;
import com.glenwood.glaceemr.server.application.models.ServiceDetail;
import com.glenwood.glaceemr.server.application.models.ServiceDetail_;
import com.glenwood.glaceemr.server.application.models.SubmitStatus;

public class ChargesSpecification {
   
    public static Specification<PosTable> checkPosIsActive() {

        return new Specification<PosTable>() {

            @Override
            public Predicate toPredicate(Root<PosTable> root, CriteriaQuery<?> cq,
                    CriteriaBuilder cb) {
                Predicate posTableIsActive = cb.equal(root.get(PosTable_.posTableIsActive),true);
                cq.orderBy(cb.desc(root.get(PosTable_.posTableFacilityComments)));
                return posTableIsActive;
            }
        };
    }

    public static Specification<EmployeeProfile> getAllEmployeeDetails() {
        return new Specification<EmployeeProfile>() {

            @Override
            public Predicate toPredicate(Root<EmployeeProfile> root,
                    CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate employee=cb.and((root.get(EmployeeProfile_.empProfileGroupid).in("-1","-2","-10","-25")),(cb.equal(root.get(EmployeeProfile_.empProfileIsActive), true)));
                return employee;
            }
        };
    }

    public static Specification<PatientInsDetail> getInsDetails(final Integer patientId) {
        return new Specification<PatientInsDetail>() {

            @Override
            public Predicate toPredicate(Root<PatientInsDetail> root,
                    CriteriaQuery<?> query, CriteriaBuilder cb) {
                Join<PatientInsDetail,InsCompAddr> insCompAddr=root.join(PatientInsDetail_.insCompAddr,JoinType.INNER);
                Join<Join<PatientInsDetail,InsCompAddr>, InsCompany> insComp=insCompAddr.join("insCompany",JoinType.INNER);
                Predicate patientIdValidation=cb.equal(root.get(PatientInsDetail_.patientInsDetailPatientid),patientId);
                if (Long.class != query.getResultType()) {
                    root.fetch(PatientInsDetail_.insCompAddr).fetch(InsCompAddr_.insCompany);
                }
                return query.where(patientIdValidation).orderBy(cb.asc(root.get(PatientInsDetail_.patientInsDetailInstype))).getRestriction();
            }
        };
    }

    /*public static Specification<Quickcpt> findAllQuickCptCodes() {
        return new Specification<Quickcpt>() {
           
            @Override
            public Predicate toPredicate(Root<Quickcpt> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {
                Join<Quickcpt, Cpt> cptJoin=root.join(Quickcpt_.cpt,JoinType.LEFT);
                if (Long.class != query.getResultType()) {
                    root.fetch(Quickcpt_.cpt);
//                }
                return query.orderBy(cb.asc(root.get(Quickcpt_.groupNo))).getRestriction();
            }
        };
    }*/

    public static Specification<ReferringDoctor> getAllReferringDrList() {
        return new Specification<ReferringDoctor>() {
           
            @Override
            public Predicate toPredicate(Root<ReferringDoctor> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {
                Predicate referingDoctorActive = cb.and(cb.isTrue(root.get(ReferringDoctor_.referringDoctorIsactive)),root.get(ReferringDoctor_.referringDoctorRdoctorid).isNotNull(),root.get(ReferringDoctor_.referringDoctorFirstname).isNotNull(),root.get(ReferringDoctor_.referringDoctorReferringdoctor).isNotNull());
                return query.where(referingDoctorActive).orderBy(cb.asc(root.get(ReferringDoctor_.referringDoctorRdoctorid))).getRestriction();
            }
        };
    }

    public static Specification<ServiceDetail> findAllServices(final Integer patientid) {
        return new Specification<ServiceDetail>() {
           
            @Override
            public Predicate toPredicate(Root<ServiceDetail> root,
                    CriteriaQuery<?> query, CriteriaBuilder cb) {
                try {
                    Join<ServiceDetail,Cpt> cptJoin=root.join(ServiceDetail_.cpt,JoinType.LEFT);
                    Join<ServiceDetail, SubmitStatus> submitStatus=root.join(ServiceDetail_.submitStatus,JoinType.LEFT);
                    if (Long.class != query.getResultType()) {
                        root.fetch(ServiceDetail_.cpt);
                        root.fetch(ServiceDetail_.submitStatus);
                    }
//                    Fetch<ServiceDetail, Cpt> cptJoin=root.fetch(ServiceDetail_.cpt,JoinType.LEFT);
                    Predicate patientId=cb.equal(root.get(ServiceDetail_.serviceDetailPatientid), patientid);
                    Predicate cptTypeCheck=cb.or(cb.equal(cptJoin.get(Cpt_.cptCpttype), 1),cb.like(cptJoin.get(Cpt_.cptCptcode),"%novisit%" ));
                    query.where(patientId,cptTypeCheck).orderBy(cb.desc(root.get(ServiceDetail_.serviceDetailDos)),cb.desc(root.get(ServiceDetail_.serviceDetailId)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return query.getRestriction();
            }
        };
    }

    public static Specification<ServiceDetail> findAllDetailsofAService(final Integer patientid,final Integer serviceid) {
        return new Specification<ServiceDetail>() {

            @Override
            public Predicate toPredicate(Root<ServiceDetail> root,
                    CriteriaQuery<?> query, CriteriaBuilder cb) {
                /*Join<ServiceDetail,Cpt> cptJoin=root.join(ServiceDetail_.cpt,JoinType.LEFT);
                Join<ServiceDetail, EmpProfile> sDocotorDetails=root.join(ServiceDetail_.sdoctors,JoinType.LEFT);
                Join<ServiceDetail, EmpProfile> rDocotorDetails=root.join(ServiceDetail_.bdoctors,JoinType.LEFT);
                Join<ServiceDetail, PosTable> posDetails=root.join(ServiceDetail_.posTable,JoinType.LEFT);
                Join<ServiceDetail,PatientInsDetail> insDetails=root.join(ServiceDetail_.patientInsDetail,JoinType.LEFT);
                Join<Join<ServiceDetail,PatientInsDetail> ,InsCompAddr> inscompAddr=insDetails.join("insCompAddr",JoinType.LEFT);
                Join<Join<Join<ServiceDetail,PatientInsDetail> ,InsCompAddr>,InsCompany> insCompany=inscompAddr.join("insCompany",JoinType.LEFT);
                Join<ServiceDetail,PatientInsDetail> secInsDetails=root.join(ServiceDetail_.secInsDetail,JoinType.LEFT);
                Join<Join<ServiceDetail,PatientInsDetail> ,InsCompAddr> secInscompAddr=secInsDetails.join("insCompAddr",JoinType.LEFT);
                Join<Join<Join<ServiceDetail,PatientInsDetail> ,InsCompAddr>,InsCompany> secInsCompany=secInscompAddr.join("insCompany",JoinType.LEFT);
                if (Long.class != query.getResultType()) {*/
                    root.fetch(ServiceDetail_.cpt,JoinType.LEFT);
                    root.fetch(ServiceDetail_.sdoctors,JoinType.LEFT);
                    root.fetch(ServiceDetail_.bdoctors,JoinType.LEFT);
                    root.fetch(ServiceDetail_.posTable,JoinType.LEFT);
                    root.fetch(ServiceDetail_.patientInsDetail,JoinType.LEFT);
                    root.fetch(ServiceDetail_.secInsDetail,JoinType.LEFT);
//                    root.fetch(ServiceDetail_.authorizationReferral,JoinType.LEFT);
//                }
                Predicate finalPredicate=cb.and(cb.equal(root.get(ServiceDetail_.serviceDetailPatientid), patientid),cb.equal(root.get(ServiceDetail_.serviceDetailId),serviceid));
           
                return query.where(finalPredicate).getRestriction();
            }
        };
    }

    public static Specification<ServiceDetail> getAllServices(final List<Integer> idValues,final Integer patientId) {
        return new Specification<ServiceDetail>() {

            @Override
            public Predicate toPredicate(Root<ServiceDetail> root,
                    CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate patientIdValidation=cb.equal(root.get(ServiceDetail_.serviceDetailPatientid), patientId);
                Predicate getServices=root.get(ServiceDetail_.serviceDetailId).in(idValues);
                Predicate finalPredicate=cb.and(patientIdValidation,getServices);
                return finalPredicate;
            }
        };
    }
    public static Specification<ServiceDetail> getMaxDos(final Integer patientId) {
        return new Specification<ServiceDetail>() {

            @Override
            public Predicate toPredicate(Root<ServiceDetail> root,
                    CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate maxValue=cb.equal(root.get(ServiceDetail_.serviceDetailPatientid), patientId);
                return maxValue;
            }
        };
    }
    public static Specification<ServiceDetail> getPreviousVisittDxCodes(final Integer patientId,final Date serviceDetailDos) {
        return new Specification<ServiceDetail>() {
           
            @Override
            public Predicate toPredicate(Root<ServiceDetail> root,
                    CriteriaQuery<?> query, CriteriaBuilder cb) {
                Date currentDate=new Date(Calendar.getInstance().getTime().getTime());
                Predicate patientIdChecking=cb.equal(root.get(ServiceDetail_.serviceDetailPatientid), patientId);
                Predicate dosChecking=cb.equal(root.get(ServiceDetail_.serviceDetailDos), serviceDetailDos);
                return query.where(patientIdChecking,dosChecking).orderBy(cb.desc(root.get(ServiceDetail_.serviceDetailId))).getRestriction();
            }
        };
    }

    public static Specification<Cpt> getCptCodeDetails(final List<String> cptCodes) {
        return new Specification<Cpt>() {

            @Override
            public Predicate toPredicate(Root<Cpt> root,
                    CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate cptCodeValue=root.get(Cpt_.cptCptcode).in(cptCodes);
                return query.where(cptCodeValue).getRestriction();
            }
        };
    }
    public static Specification<PrimarykeyGenerator> getServiceDetailMaxId() {
        return new Specification<PrimarykeyGenerator>() {

            @Override
            public Predicate toPredicate(Root<PrimarykeyGenerator> root,
                    CriteriaQuery<?> query, CriteriaBuilder cb) {
               
                Predicate maxValue=cb.equal(root.get(PrimarykeyGenerator_.primarykeyGeneratorTablename), "service_detail");
                return maxValue;
            }
        };
    }

    public static Specification<H066> getExpectedPayment(final Integer cptId,final String modifier1,
            final String modifier2,final String placeofServiceId,final int dosYear,
            final Integer primaryInsuraceId) {
        return new Specification<H066>() {

            @Override
            public Predicate toPredicate(Root<H066> root,
                    CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate conditionalPredicate=cb.and(cb.equal(root.get(H066_.h066002),cptId),cb.equal(root.get(H066_.h066003),modifier1),cb.equal(root.get(H066_.h066004),modifier2),cb.equal(root.get(H066_.h066009),dosYear));
                return query.where(conditionalPredicate).orderBy(cb.desc(root.get(H066_.h066001))).getRestriction();
            }
        };
    }

    public static Specification<NonServiceDetails> getPaymentInfo(final List<Integer> idValues, Integer patientId) {
        return new Specification<NonServiceDetails>() {

            @Override
            public Predicate toPredicate(Root<NonServiceDetails> root,
                    CriteriaQuery<?> query, CriteriaBuilder cb) {
                Join<NonServiceDetails, Cpt> paymentVerfication=root.join(NonServiceDetails_.cpt,JoinType.INNER);
                Predicate cptType=paymentVerfication.get(Cpt_.cptCpttype).in(1,2);
                Predicate  serviceId=root.get(NonServiceDetails_.nonServiceDetailServiceId).in(idValues);
                return query.where(cptType,serviceId).getRestriction();
            }
        };
    }

    public static Specification<ServiceDetail> checkDupliacteService(
            final Integer patientid,final Date dateofService,final String modifier1,
            final String modifier2,final String modifier3,final String modifier4,final String cptId) {
        return new Specification<ServiceDetail>() {

            @Override
            public Predicate toPredicate(Root<ServiceDetail> root,
                    CriteriaQuery<?> query, CriteriaBuilder cb) {
//                Join<ServiceDetail, Cpt> serviceEntity=root.join(ServiceDetail_.cpt,JoinType.INNER);
                Predicate concatinatedPredicate=cb.and(cb.equal(root.get(ServiceDetail_.serviceDetailPatientid),patientid),
                        cb.equal(root.get(ServiceDetail_.serviceDetailDos), dateofService),
                        cb.equal(root.get(ServiceDetail_.serviceDetailCptid), cptId),
                        cb.equal(root.get(ServiceDetail_.serviceDetailModifier1), modifier1),
                        cb.equal(root.get(ServiceDetail_.serviceDetailModifier2), modifier2),
                        cb.equal(root.get(ServiceDetail_.serviceDetailModifier3), modifier3),
                        cb.equal(root.get(ServiceDetail_.serviceDetailModifier4), modifier4),
                        cb.equal(root.get(ServiceDetail_.isblocked), false));
                return query.where(concatinatedPredicate).getRestriction();
            }
        };
    }

}