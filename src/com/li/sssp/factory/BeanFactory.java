package com.li.sssp.factory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.li.sssp.factory.BeanEnum.BaseDataBean;
import com.li.sssp.factory.BeanEnum.NetDataBean;
import com.li.sssp.factory.BeanEnum.NetResults;
import com.li.sssp.factory.BeanEnum.UntilBean;
import com.li.sssp.inter.IBaseData;
import com.li.sssp.inter.IJson;
import com.li.sssp.inter.INetData;
import com.li.sssp.inter.INetResults;

@Component
public class BeanFactory implements ApplicationContextAware {

	private static Map<NetDataBean, INetData> NetDatamap;
	private static Map<UntilBean, IJson> untilMap;
	private static Map<NetResults,INetResults> netResultsMap;
	private static Map<BaseDataBean,IBaseData> baseDataMap;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		
		System.out.println("----------------NetFactory---------------------");
		NetDatamap = new HashMap<>();
		Map<String, INetData> _netDatamap = applicationContext.getBeansOfType(INetData.class);
		for (INetData value : _netDatamap.values()) {
			System.out.println(value.getCode());
			NetDatamap.put(value.getCode(), value);
		}
		
		System.out.println("----------------UntilFactory---------------------");
		untilMap = new HashMap<>();
		Map<String, IJson> _untilmap = applicationContext.getBeansOfType(IJson.class);
		for (IJson value : _untilmap.values()) {
			System.out.println(value.getCode());
			untilMap.put(value.getCode(), value);
		}
		
		System.out.println("----------------NetResultFactory---------------------");
		netResultsMap=new HashMap<>();
		Map<String,INetResults> _netResults=applicationContext.getBeansOfType(INetResults.class);
		for (INetResults value:_netResults.values()){
			System.out.println(value.getCode());
			netResultsMap.put(value.getCode(), value);
		}
		
		System.out.println("----------------BaseDataFactory---------------");
		baseDataMap=new HashMap<>();
		Map<String,IBaseData> _baseDatas=applicationContext.getBeansOfType(IBaseData.class);
		for(IBaseData value:_baseDatas.values()){
			System.out.print(value.getCode());
			baseDataMap.put(value.getCode(), value);
			
		}
		
		

	}

	
	
	public static <T extends INetData> T getNetDataBean(NetDataBean netDataBean) {
		return (T) NetDatamap.get(netDataBean);
	}
	
	public static <T extends IJson> T getUntilBean(UntilBean untilBean){
		return (T) untilMap.get(untilBean);
	}
	public static <T extends INetResults> T getNetResultBean(NetResults netResults){
		return (T) netResultsMap.get(netResults);
	}
	public static <T extends IBaseData> T getBaseDataBean(BaseDataBean baseDataBean){
		return (T) baseDataMap.get(baseDataBean);
	}

}
