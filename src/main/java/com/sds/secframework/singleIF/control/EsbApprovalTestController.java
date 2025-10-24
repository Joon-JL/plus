/**
 * 
 */
package com.sds.secframework.singleIF.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import mySingle.service.APStatusInquiryServiceLocator;

import org.springframework.web.servlet.ModelAndView;

import samsung.esb.approval.service.APStatusInquiryService;
import samsung.esb.approval.vo.MisKeyWSVO;
import samsung.esb.approval.vo.ProcessRevisionWSVO;
import samsung.esb.common.vo.ESBAuthVO;
import samsung.esb.common.vo.ESBFaultVO;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.EsbUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.service.EsbApprovalService;
import com.sds.secframework.singleIF.service.EsbOrgService;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.clm.sign.control.SignManageController;
import com.sec.clm.sign.service.SignManageService;



/**
 * @author joonh.lee
 *
 */
public class EsbApprovalTestController extends CommonController {
	
	private EsbApprovalTestController esbApprovalTestController;
	
	private SignManageService signManageService;
	public void setSignManageService(SignManageService signManageService) {
		this.signManageService = signManageService;
	}	

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbApprovalService esbApprovalService;
	public void setEsbApprovalService(EsbApprovalService esbApprovalService) {
		this.esbApprovalService = esbApprovalService;
	}
	
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbOrgService esbOrgService;
	public void setEsbOrgService(EsbOrgService esbOrgService) {
		this.esbOrgService = esbOrgService;
	}
	
	// ComUtil 서비스
	private ComUtilService comUtilService;

	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
	}

	public ModelAndView initApprovalTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		getLogger().info("==initApprovalTest==");
		
		String moduleId = "LAS";
		ModelAndView mav = new ModelAndView();		
		
		try {
							
			String pageMode = StringUtil.bvl(request.getParameter("status_mode"),"");
			mav.addObject("status_mode", "GOOD");
			
			String misId = StringUtil.bvl(request.getParameter("misId"),"");			
			this.getLogger().info("misId: " +misId);
			
			String misIds[] = {
					misId,
					"APPR20150310215922KV1HWAQR1CBQRZ", // 03/10 20150310215922
					"APPR20140613210431PVAWGQBAGPC02O", // 20140613210431
					"APPR20150305015520WZTRO3IAJ3UQKA" // Complete
			};
			
			
			ProcessRevisionWSVO[]  processRevisionWSVOs = this.getRevisionByMisidTest(misIds);
			
			getLogger().info("= Complete, RevisionESB ==" + processRevisionWSVOs.length);
			
			for (int i =0 ; i < processRevisionWSVOs.length ; i++ ) {
				getLogger().info("= For STMT =");
				String eachMisId, eachProcinstID, eachRevision, eachStatus;
				ProcessRevisionWSVO processRevisionWSVO = processRevisionWSVOs[i];
				
				eachMisId = processRevisionWSVO.getMisID();
				eachProcinstID = processRevisionWSVO.getProcinstID();
				eachRevision = processRevisionWSVO.getRevision();
				eachStatus = processRevisionWSVO.getStatus();
				
				getLogger().info(" # eachMisId :"+ eachMisId);
				getLogger().info(" # eachProcinstID :"+ eachProcinstID);
				getLogger().info(" # eachRevision :"+ eachRevision);
				getLogger().info(" # eachStatus :"+ eachStatus);				
				
			}
			
			mav.setViewName("/WEB-INF/jsp/clm/common/mySingleApprovalTest.jsp");
			this.getLogger().info("forwardURL: " +mav.getViewName());
			
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
		
		return mav;
	}
	
	public ProcessRevisionWSVO[] getRevisionByMisidTest(String[] ls_misId ) throws Exception {
		

		String esbApprId = "C10AP0673";
		String esbApprPw = "C10AP0673248630";
		
		ProcessRevisionWSVO[]  processRevisionWSVOs = new ProcessRevisionWSVO[ls_misId.length];	

		ESBAuthVO esbAuthVO = EsbUtil.getESBAuthVO(esbApprId, esbApprPw);	getLogger().info("= 134 ====");	
		APStatusInquiryServiceLocator serviceLoacator = new APStatusInquiryServiceLocator();		getLogger().info("= 135 ====");
		
		try {
			getLogger().info("= Try ====");
			
			APStatusInquiryService apStatusInquiryService = serviceLoacator.getAPStatusInquiryService_InboundPort();

			MisKeyWSVO[] misKeyWSVOs = new MisKeyWSVO[ls_misId.length];
			
			getLogger().info("leng="+ls_misId.length);
			
			
			for (int y=0;y < ls_misId.length;y++) {
				
				getLogger().info("each MIS ID["+ y +"]="+ls_misId[y]);

				MisKeyWSVO misKeyWSVO = new MisKeyWSVO();
				misKeyWSVO.setMisID(ls_misId[y]);
				misKeyWSVO.setSystemID(esbApprId);
				
				misKeyWSVOs[y] = misKeyWSVO;
				//misKeyWSVO[y] .setMisID(ls_misId[y]);
				
				//processStatusWSVO[y] = apStatusInquiryService.getRevisionByMisId(esbAuthVO, misKeyWSVO);
				//getLogger().info(" === " + misKeyWSVOs[y].getMisID());
				
				
			}
			
			//System.out.println("===> " + apStatusInquiryService.getRevisionByMisId(esbAuthVO, misKeyWSVO));
			
			processRevisionWSVOs = apStatusInquiryService.getRevisionByMisId(esbAuthVO, misKeyWSVOs);
			
		} catch(ESBFaultVO fault) {
			fault.printStackTrace();	
			getLogger().error("===ESBFaultVO=" + fault.toString() );
			
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			getLogger().error("Exception : " + e.toString());
			e.printStackTrace();
		}
				
		return processRevisionWSVOs;
	
	}
	
}
