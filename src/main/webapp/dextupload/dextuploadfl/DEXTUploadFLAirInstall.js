/**
 * InstallAirApplication.swf를 로드하는 object 구성을 생성합니다.
 * 수정하지 마세요.
 */
var createDEXTUploadFLAirInstaller = function(installerUrl, options) {
	var insUrl = installerUrl;
	var opt = {
			backcolor: "ffffff",
    		airversion: "2.7",
    		forceair: "true",
			appid: "com.devpia.dextuploadflmultidownloader",           		
    		appversion: "1.5.0.0",
    		appurl: "" // http://localhost:8433/fx/MultiFileDownloadMonitor.air
	};
	
	for (var optName in options) {
		opt[optName] = options[optName];
	}
	
	var flashVars = "" + 
		"backcolor=" + opt.backcolor + "&" +
		"airversion=" + opt.airversion + "&" +
		"forceair=" + opt.forceair + "&" +
		"appid=" + opt.appid + "&" +
		"appversion=" + opt.appversion + "&" +
		"appurl=" + opt.appurl;
	
	var objectTag = "" +
		"<object type='application/x-shockwave-flash'" +
		"classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000'" +
		"codebase='http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=10,3,0,0'" +
		"style='width:100%;height:100%' id='InstallerAirApplication'>" +
		"<param name='movie' value='" + insUrl + "' />" +
		"<param name='quality' value='high' />" +
		"<param name='bgcolor' value='#FFFFFF' />" +
		"<param name='allowScriptAccess' value='always' />" +
		"<param name='allowFullScreen' value='true' />" +
		"<param name='wmode' value='window' />" +
		"<param name='flashvars' value='" + flashVars + "'/>" +
		"<!--[if !IE]>" +
		"<object type='application/x-shockwave-flash' data='" + insUrl + "' style='width:100%;height:100%'>" +
		"<param name='movie' value='" + insUrl + "' />" +
		"<param name='quality' value='high' />" +
		"<param name='bgcolor' value='#FFFFFF' />" +
		"<param name='allowScriptAccess' value='always' />" +
		"<param name='allowFullScreen' value='true' />" +
		"<param name='wmode' value='window' /> " +
		"<param name='flashvars' value='" + flashVars + "'/>" +
		"<param name='pluginurl' value='http://www.adobe.com/go/getflashplayer'>" +
		"<embed src='" + insUrl + "' quality='high' width='0' height='0' allowScriptAccess='always' type='application/x-shockwave-flash' pluginspage='http://www.macromedia.com/go/getflashplayer' flashvars='" + flashVars + "'/>" +
		"" +
		" <![endif]-->" +
		"<!--[if gte IE 6]>" +
		" <p> " +
		" Either scripts and active content are not permitted to run or Adobe Flash Player version" +
		" 10.0.0 or greater is not installed." +
		" </p>" +
		"<![endif]-->" +
		" <a href='http://www.adobe.com/go/getflashplayer'>" +
		" <img src='http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif' alt='Get Adobe Flash Player' />" +
		" </a>" +
		"<!--[if !IE]>" +
		"</object>";

	
	document.write(objectTag);
};

var createDEXTUploadFL2AirInstaller = function(installerUrl, options) {
	var insUrl = installerUrl;
	var opt = {
			backcolor: "ffffff",
    		airversion: "2.7",
    		forceair: "true",
			appid: "com.devpia.dextuploadflmultidownloader2",           		
    		appversion: "2.1.0.0",
    		appurl: "" // http://localhost/DEXTUploadFL2Samples/dextuploadfl/MultiFileDownloadMonitor.air
	};
	
	for (var optName in options) {
		opt[optName] = options[optName];
	}
	
	var flashVars = "" + 
		"backcolor=" + opt.backcolor + "&" +
		"airversion=" + opt.airversion + "&" +
		"forceair=" + opt.forceair + "&" +
		"appid=" + opt.appid + "&" +
		"appversion=" + opt.appversion + "&" +
		"appurl=" + opt.appurl;
	
	var objectTag = "" +
		"<object type='application/x-shockwave-flash'" +
		"classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000'" +
		"codebase='http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=10,3,0,0'" +
		"style='width:100%;height:100%' id='InstallerAirApplication'>" +
		"<param name='movie' value='" + insUrl + "' />" +
		"<param name='quality' value='high' />" +
		"<param name='bgcolor' value='#FFFFFF' />" +
		"<param name='allowScriptAccess' value='always' />" +
		"<param name='allowFullScreen' value='true' />" +
		"<param name='wmode' value='window' />" +
		"<param name='flashvars' value='" + flashVars + "'/>" +
		"<!--[if !IE]>" +
		"<object type='application/x-shockwave-flash' data='" + insUrl + "' style='width:100%;height:100%'>" +
		"<param name='movie' value='" + insUrl + "' />" +
		"<param name='quality' value='high' />" +
		"<param name='bgcolor' value='#FFFFFF' />" +
		"<param name='allowScriptAccess' value='always' />" +
		"<param name='allowFullScreen' value='true' />" +
		"<param name='wmode' value='window' /> " +
		"<param name='flashvars' value='" + flashVars + "'/>" +
		"<param name='pluginurl' value='http://www.adobe.com/go/getflashplayer'>" +
		"<embed src='" + insUrl + "' quality='high' width='0' height='0' allowScriptAccess='always' type='application/x-shockwave-flash' pluginspage='http://www.macromedia.com/go/getflashplayer' flashvars='" + flashVars + "'/>" +
		"" +
		"<![endif]-->" +
		"<!--[if gte IE 6]>" +
		" <p> " +
		" Either scripts and active content are not permitted to run or Adobe Flash Player version" +
		" 10.0.0 or greater is not installed." +
		" </p>" +
		"<![endif]-->" +
		"<!--[if !IE]>" +
		" <a href='http://www.adobe.com/go/getflashplayer'>" +
		" <img src='http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif' alt='Get Adobe Flash Player' />" +
		" </a>" +
		"<![endif]-->" +
		"</object>";

	
	document.write(objectTag);
};