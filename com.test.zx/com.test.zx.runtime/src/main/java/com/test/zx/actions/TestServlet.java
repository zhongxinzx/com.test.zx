package com.test.zx.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.zx.lib.SpringContextUtil;
import com.test.zx.lib.StringUtil;
import com.test.zx.service.api.MyService;
import com.tydic.rms.service.RmsGatewayReportService;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*@Autowired
	private MyService myService;*/
    /**
     * Default constructor. 
     */
    public TestServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = (String)request.getParameter("name");
		if (StringUtil.isEmpty(userName)) {
			response.getWriter().append("用户为空！");
		} else {
			MyService myService = (MyService) SpringContextUtil.getBeanById("myService");
			myService.saveStringToRedis("name", userName);
			response.getWriter().append("Hello: ").append((String)request.getParameter("name"));
			
			
			RmsGatewayReportService rmsGatewayReportService = (RmsGatewayReportService) SpringContextUtil.getBeanById("rmsGatewayReportService");
			// String param = "{\"OpenId\":\"admin\",\"endTime\":\"2016-12-19 23:59:59\",\"ip\":\"192.168.205.161\",\"pageNo\":1,\"pageSize\":10,\"regions\":[{\"level2\":\"1\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"\",\"regionId\":\"53\",\"regionName\":\"重庆\"},{\"level2\":\"2\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"53\",\"regionId\":\"89\",\"regionName\":\"两江新区\"},{\"level2\":\"2\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"53\",\"regionId\":\"333\",\"regionName\":\"XXXx\"},{\"level2\":\"2\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"53\",\"regionId\":\"3412\",\"regionName\":\"云阳区\"},{\"level2\":\"2\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"53\",\"regionId\":\"5310\",\"regionName\":\"渝北区\"},{\"level2\":\"3\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"5310\",\"regionId\":\"2333\",\"regionName\":\"阿斯达斯死\"},{\"level2\":\"3\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"5310\",\"regionId\":\"5311\",\"regionName\":\"渝北\"},{\"level2\":\"3\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"5310\",\"regionId\":\"131314\",\"regionName\":\"大兴村\"},{\"level2\":\"2\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"53\",\"regionId\":\"5312\",\"regionName\":\"江北区\"},{\"level2\":\"3\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"5312\",\"regionId\":\"5313\",\"regionName\":\"观音桥\"},{\"level2\":\"2\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"53\",\"regionId\":\"5314\",\"regionName\":\"沙坪坝区\"},{\"level2\":\"3\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"5314\",\"regionId\":\"5326\",\"regionName\":\"梨树湾\"},{\"level2\":\"2\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"53\",\"regionId\":\"5315\",\"regionName\":\"万州区\"},{\"level2\":\"3\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"5315\",\"regionId\":\"530000\",\"regionName\":\"大佛寺\"},{\"level2\":\"2\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"53\",\"regionId\":\"5316\",\"regionName\":\"涪陵区\"},{\"level2\":\"3\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"5316\",\"regionId\":\"530001\",\"regionName\":\"小孔\"},{\"level2\":\"2\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"53\",\"regionId\":\"5317\",\"regionName\":\"黔江区\"},{\"level2\":\"3\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"5317\",\"regionId\":\"530002\",\"regionName\":\"大陆\"},{\"level2\":\"2\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"53\",\"regionId\":\"5318\",\"regionName\":\"长寿区\"},{\"level2\":\"3\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"5318\",\"regionId\":\"530003\",\"regionName\":\"常山\"},{\"level2\":\"2\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"53\",\"regionId\":\"5319\",\"regionName\":\"大足区\"},{\"level2\":\"3\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"5319\",\"regionId\":\"123123\",\"regionName\":\"大大\"},{\"level2\":\"3\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"5319\",\"regionId\":\"530004\",\"regionName\":\"组不\"},{\"level2\":\"3\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"5319\",\"regionId\":\"530005\",\"regionName\":\"搭讪\"},{\"level2\":\"2\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"53\",\"regionId\":\"5320\",\"regionName\":\"双桥区\"},{\"level2\":\"2\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"53\",\"regionId\":\"5321\",\"regionName\":\"万盛区\"},{\"level2\":\"2\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"53\",\"regionId\":\"5322\",\"regionName\":\"北碚区\"},{\"level2\":\"2\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"53\",\"regionId\":\"5323\",\"regionName\":\"南岸区\"},{\"level2\":\"2\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"53\",\"regionId\":\"5324\",\"regionName\":\"九龙坡区\"},{\"level2\":\"2\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"53\",\"regionId\":\"5325\",\"regionName\":\"大渡口区\"},{\"level2\":\"2\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"53\",\"regionId\":\"5398\",\"regionName\":\"XXX\"},{\"level2\":\"2\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"53\",\"regionId\":\"12312\",\"regionName\":\"啊啊啊啊啊啊\"},{\"level2\":\"3\",\"levelOneId\":\"53\",\"levelOneName\":\"重庆\",\"pid\":\"12312\",\"regionId\":\"123451\",\"regionName\":\"哦哦哦哦\"}],\"roleName\":\"超级管理员\",\"searchType\":0,\"startTime\":\"2016-12-05 00:00:00\",\"uid\":\"admin\",\"userName\":\"admin\"}";
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("startTime", "2016-11-01 00:00:00");
			param.put("endTime", "2016-11-30 00:00:00");
			param.put("pageNo", "1");
			param.put("pageSize", "10");
			param.put("searchType", 0);
			List<Map<String, Object>> regionList = new ArrayList<Map<String, Object>>();
			Map<String, Object> region = new HashMap<String, Object>();
			region.put("regionId", 5310);
			region.put("regionName", "江北");
			regionList.add(region);
			
			region = new HashMap<String, Object>();
			region.put("regionId", 5312);
			region.put("regionName", "渝北");
			regionList.add(region);
			
			region = new HashMap<String, Object>();
			region.put("regionId", 5313);
			region.put("regionName", "渝中");
			regionList.add(region);
			
			param.put("regions", regionList);
			Map<String, Object> result = rmsGatewayReportService.getWideBandStatistics(param);
			System.out.println(result.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
