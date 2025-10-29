function checkUseSyncEXE()
{
		var useSyncExe;
    if(navigator.userAgent.toLowerCase().indexOf("windows")!=-1
       && navigator.userAgent.toLowerCase().indexOf("msie")==-1){
        // 윈도우즈고 IE가 아닌경우에는 중간 프로그램을 쓰겠다.
		useSyncExe = true; 
    }else{
    	useSyncExe = false;  	
    }

	return useSyncExe;
}