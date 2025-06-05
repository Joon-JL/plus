package com.sec.clm.search.common.provider;


public abstract class Provider {
	private static Provider instance;
	
    protected static Provider getInstance(Class<? extends Provider> cls) throws InstantiationException, IllegalAccessException{
		instance = cls.newInstance();
    	return instance;
    }
	public abstract Object provide();
	
	public abstract Object provide(String host, int port);
}
