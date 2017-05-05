package com.text.zx.webservice.test;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;

public class WebServiceClientTest {
	@Test
	public void doAixs2Test() {
		/*try {
			Options options = new Options();
			// 指定调用WebService的URL
			EndpointReference targetEPR = new EndpointReference("http://127.0.0.1:8000/bs/services/OpenCbcBUnitedInterfaces?wsdl");
			options.setTo(targetEPR);
			// options.setAction("urn:getPrice");

			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);

			OMFactory fac = OMAbstractFactory.getOMFactory();
			String tns = "http://bodbusiness.ws.brsu.xbsafe.cn/";
			// 命名空间，有时命名空间不增加没事，不过最好加上，因为有时有事，你懂的
			OMNamespace omNs = fac.createOMNamespace(tns, "");

			OMElement method = fac.createOMElement("OBSQueryQosByActualTime", omNs);
			OMElement symbol = fac.createOMElement("queryQosActualTimeRequest", omNs);
			OMElement password = fac.createOMElement("password", omNs);
			OMElement taskcode = fac.createOMElement("taskcode", omNs);
			OMElement taskname = fac.createOMElement("taskname", omNs);
			OMElement request = fac.createOMElement("request", omNs);
			
			OMElement acct = fac.createOMElement("acct", omNs);
			OMElement brsuid = fac.createOMElement("brsuid", omNs);
			request.addChild(acct);
			request.addChild(brsuid);
			
			acct.addChild(fac.createOMText(acct, "cq1390009217"));
			brsuid.addChild(fac.createOMText(brsuid, "201609051649300001"));
			
			symbol.addChild(password);
			symbol.addChild(taskcode);
			symbol.addChild(taskname);
			symbol.addChild(request);
			
			method.addChild(symbol);
			method.build();
			System.out.println(method);
			OMElement result = sender.sendReceive(method);

			System.out.println(result);
		} catch (AxisFault axisFault) {
			axisFault.printStackTrace();
		}*/
		
		Service service = new Service();
		try {
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL("http://127.0.0.1:8000/bs/services/OpenCbcBUnitedInterfaces?wsdl"));
			call.setOperationName(new QName("http://bodbusiness.ws.brsu.xbsafe.cn", "OBSQueryQosByActualTime"));
			QName qn = new QName("tns:queryQosActualTimeRequest", "queryQosActualTimeRequest");
			call.registerTypeMapping(QueryQosActualTimeRequest.class, qn, new BeanSerializerFactory(QueryQosActualTimeRequest.class, qn), new BeanDeserializerFactory(QueryQosActualTimeRequest.class, qn));
			call.addParameter("info", qn, ParameterMode.IN);
			call.setReturnType(XMLType.XSD_STRING);
			Object[] params = new Object[] { new QueryQosActualTimeRequest() };
			String result = (String) call.invoke(params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void doCxfTest() {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance(); 
	    String wsUrl = "http://127.0.0.1:8000/bs/services/OpenCbcBUnitedInterfaces?wsdl"; 
	    Client client = dcf.createClient(wsUrl);
	    String method = "OBSQueryQosByActualTime";//webservice的方法名 
	    Object[] result = null;
	    try {
	       result = client.invoke(method, new QueryQosActualTimeRequest());//调用webservice 
	    } catch (Exception e) {
	       e.printStackTrace();
	    }
	}
	
}

