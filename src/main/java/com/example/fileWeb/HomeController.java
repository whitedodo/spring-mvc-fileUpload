package com.example.fileWeb;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public String insert(Model model) {
		
		model.addAttribute("list", "");
		
		return "insert";
	}
	
	@RequestMapping(value = "uploadResult", method = RequestMethod.POST)
	public String insertResult(Model model, MultipartHttpServletRequest req) {
		
		model.addAttribute("list", "");

		// 루트 경로
        String rootUploadDir = "C:"+File.separator+"Upload"; // C:/Upload
        File dir = new File(rootUploadDir + File.separator + "testfile"); 
        
        if(!dir.exists()) { //업로드 디렉토리가 존재하지 않으면 생성
            dir.mkdirs();
        }
		
		Iterator<String> iterator = req.getFileNames(); 	//	다중 업로드 지원
        ArrayList<String> list = new ArrayList<String>();
        
        int fileLoop = 0;
        String uploadFileName;
        MultipartFile mFile = null;
        String orgFileName = "";					// 진짜 파일명
        String sysFileName = ""; 					// 변환된 파일명
        
        list.add("루트경로:" + dir + File.separator );
        
        while(iterator.hasNext()) {
        	
            fileLoop++;
            
            uploadFileName = iterator.next();
            mFile = req.getFile(uploadFileName);
            
            orgFileName = mFile.getOriginalFilename();
            logger.info(orgFileName);
            
            if(orgFileName != null && orgFileName.length() != 0) { //sysFileName 생성
                
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMDDHHmmss-" + fileLoop);
                Calendar calendar = Calendar.getInstance();
                sysFileName = simpleDateFormat.format(calendar.getTime()); //sysFileName: 날짜-fileLoop번호
                
                
                try {
                    logger.info("try 진입");
                    mFile.transferTo(new File(dir + File.separator + sysFileName)); // C:/Upload/testfile/sysFileName
                    list.add("원본파일명: " + orgFileName + ", 시스템파일명: " + sysFileName);
                    list.add("파일크기: " + mFile.getSize() + ", 파일형식: " + mFile.getContentType() );
                    
                }catch(Exception e){
                    list.add("오류: 파일 업로드 중 에러발생!!!");
                }
                
            }//if
            
        }//while
        
        model.addAttribute("list", list);
		
		return "uploadResult";
	}
	
}
