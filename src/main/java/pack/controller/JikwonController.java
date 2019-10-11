package pack.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pack.model.DataDao;
import pack.model.JikwonDto;

@Controller
public class JikwonController {
	@Autowired //클래스니까 Qualifier할 필요 X - Qualifier는 인터페이스
	private DataDao dataDao;
	
	
	@RequestMapping("jikwon")
	public ModelAndView JikwonProcess() {
		ArrayList<JikwonDto> list = (ArrayList<JikwonDto>)dataDao.list();
		ModelAndView view = new ModelAndView("jikdata");
		view.addObject("data",list);
		return view;
	}
	
	@RequestMapping("gogek")	//get, post 가리지 않고 sangpum요청 받아~
	@ResponseBody
	public Map<String, Object> gogeks(@RequestParam("num") String num){
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		Map<String, String> data = null;
		
		
		List<JikwonDto> gogekdatas = dataDao.gogekList(num);	//dataDao가 갖고 있고 sangpumList mybatis를 통해 model에 가서 자료 읽어옴.
		System.out.println("---------------" + gogekdatas.size());
		
		for(JikwonDto s:gogekdatas) {
			data = new HashMap<String, String>();
			data.put("gogek_no", s.getGogek_no());
			data.put("gogek_name", s.getGogek_name());
			data.put("gogek_tel", s.getGogek_tel());
			dataList.add(data);	//dataList(List) 자체를 를 반환할 수 없어 Map으로 반환해야돼. 그래서!
			System.out.println(s.getGogek_name());
		}
		
		Map<String, Object> gogekData = new HashMap<String, Object>();
		gogekData.put("datas",dataList);

		//ajax는 비동기방식이라 하나가 3초가 걸리더라도 다른 작업 수행 가능.Javascript HttpRequest가 받아
		//동기방식이면 팔짱끼고 기다려야돼.
		
		return gogekData;//Map에 담아서 반환
	}
	
}

