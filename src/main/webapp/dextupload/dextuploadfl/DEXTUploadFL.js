var DEXTUploadFL = null;
// localhost 용
//var DEXTUploadFLAuthKey = "EVALFL10D42869DEFD2125BACABE0DCB37884DF2DB51DD282FFDA61EB0C51D39BB9F769EA7B104DFB6856F634E2A35946A9E29CE4A7889DEDDBD31ACDC98DAACBB54B1A0";
// slasdev.samsung.net 용
var DEXTUploadFLAuthKey = "EVALFL10F5F1F0E5BA33D484F96A847AD485EF02E8B12A83802C1F0AB91C63C7098644B804F2D931F0FD695C4CC5D38B802673596EC378AAE7E89C940868B2DFBE8F6706FD2EA339B3DDA0A3473D8E8139032781";

function isIE() { return (navigator.userAgent.indexOf("MSIE") >= 0 ? true : false); };

function getParameter(name) {
	var url = window.location.toString();
	//get the parameters
	url.match(/\?(.+)$/);
	var params = RegExp.$1;
	// split up the query string and store in an
	// associative array
	var params = params.split("&");
	
	for(var i = 0; i < params.length; i++) {
		var tmp = params[i].split("=");
		if (tmp[0] == name)
			return tmp[1];
	}
	
	return null;
};

function toArrayString(arr) {
	var str = "[ ";

	for (var item in arr) {
		if (item instanceof Array) {
			str += toArrayStrin(item) + ", ";
		} else if (typeof item == "object") {
			str += toObjectString(item) + ", ";
		} else if (typeof item == "function") {
			str += item.toString() + ", ";
		} else if (typeof item == "string") {
			str += "\"" + item.toString() + "\", ";
		} else if (typeof item == "number") {
			str += item.toString() + ", ";
		} else if (typeof item == "boolean") {
			str += item.toString() + ", ";
		} else if (typeof item != "undefinded" && item != null) {
			str += item.toString() + ", ";
		}
	}
	
	var commaIdx = str.lastIndexOf(", ");
	if (commaIdx >= 0 && (commaIdx + 2 == str.length)) {
		str = str.substring(0, commaIdx);
	}

	return str + "]";
};

function createProperty(obj, prop, value) {
	var tokens = prop.split(".");
	var curObj = obj;
	for (var i = 0; i < tokens.length; i++) {
		var name = tokens[i];
		
		if (!name) continue;
		
		if ((i + 1) == tokens.length) {
			curObj[name] = value;
		} else if (curObj.hasOwnProperty(name) === false) {
			curObj[name] = {};
		}

		curObj = curObj[name];
	}
}

function toObjectString(obj) {
	var str = "{ ";
	for (var name in obj) {
		if (obj[name] instanceof Array) {
			str += name + ": " + toArrayString(obj[name]) + ", ";
		} else if (typeof obj[name] == "object") {
			str += name + ": " + toObjectString(obj[name]) + ", ";
		} else if (typeof obj[name] == "function") {
			str += name + ": " + obj[name].toString() + ", ";
		} else if (typeof obj[name] == "string") {
			str += name + ": \"" + obj[name].toString() + "\", ";
		} else if (typeof obj[name] == "number") {
			str += name + ": " + obj[name].toString() + ", ";
		} else if (typeof obj[name] == "boolean") {
			str += name + ": " + obj[name].toString() + ", ";
		} else if (typeof obj[name] != "undefinded" && obj[name] != null) {
			str += name + ": " + obj[name].toString() + ", ";
		}
	}
	
	var commaIdx = str.lastIndexOf(", ");
	if (commaIdx >= 0 && (commaIdx + 2 == str.length)) {
		str = str.substring(0, commaIdx);
	}

	return str + " }";
};

function __DEXTUploadFLManager() {
	this.authKey	= DEXTUploadFLAuthKey;
	this.upManagers	= [];
	this.dwManagers	= [];
	this.upMonitors	= [];
	this.dwMonitors	= [];
	this.debug		= null; // function
};

__DEXTUploadFLManager.prototype.__debug = function (msg) { if (typeof(this.debug) == "function") { setTimeout(this.debug(msg), 0); } };
__DEXTUploadFLManager.prototype.getUploadManager = function (id) { return this.upManagers[id]; };
__DEXTUploadFLManager.prototype.getUploadMonitor = function (id) { return this.upMonitors[id]; };
__DEXTUploadFLManager.prototype.getDownloadManager = function (id) { return this.dwManagers[id]; };
__DEXTUploadFLManager.prototype.createUploadManager = function(target, id, swfPath, bgColor, wmode, locale, theme, uploadType, eventPostfixName) {
    var flashvars = {};
    
    flashvars.Locale		= !locale ? "" : locale;
    flashvars.Theme			= !theme ? "" : theme;
    flashvars.UploadType	= !uploadType ? "" : uploadType ;
    flashvars.AuthKey		= this.authKey; 
    flashvars.EventPostfixName = !eventPostfixName ? "" : eventPostfixName;
    
    var flashVarsStr = "";
    for (var varName in flashvars) {
    	flashVarsStr += varName + "=" + flashvars[varName] + "&";
    }

	var objectTag = "";
	
	if (navigator.userAgent.indexOf("MSIE") >= 0) {
		objectTag = objectTag +
			"<object type='application/x-shockwave-flash'" +
			"classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000'" +
			"codebase='http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=10,3,0,0'" +
			"style='width:100%;height:100%' id='" + id + "'>" +
			"<param name='movie' value='" + swfPath + "' />" +
			"<param name='quality' value='high' />" +
			"<param name='bgcolor' value='" + bgColor + "' />" +
			"<param name='allowScriptAccess' value='always' />" +
			"<param name='allowFullScreen' value='true' />" +
			"<param name='allowNetworking' value='all' />" +
			"<param name='wmode' value='" + wmode + "' />" +
			"<param name='flashvars' value='" + flashVarsStr + "'/>";
	} else {
		objectTag = objectTag +
			"<object type='application/x-shockwave-flash' data='" + swfPath + "' style='width:100%;height:100%' id='" + id + "'>" +
			"<param name='movie' value='" + swfPath + "' />" +
			"<param name='quality' value='high' />" +
			"<param name='bgcolor' value='" + bgColor + "' />" +
			"<param name='allowScriptAccess' value='always' />" +
			"<param name='allowFullScreen' value='true' />" +
			"<param name='allowNetworking' value='all' />" +
			"<param name='wmode' value='" + wmode + "' /> " +
			"<param name='flashvars' value='" + flashVarsStr + "'/>" +
			"<param name='pluginurl' value='http://www.adobe.com/go/getflashplayer'>" +
			"<embed src='" + swfPath + "' quality='high' width='0' height='0' allowNetworking='all' allowScriptAccess='always' type='application/x-shockwave-flash' pluginspage='http://www.macromedia.com/go/getflashplayer' flashvars='" + flashVarsStr + "'/>";
	}

	objectTag = objectTag +
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
	
	document.getElementById(target).innerHTML = objectTag;
	this.upManagers[id] = document.getElementById(id);
	eval("window." + id + " = document.getElementById('" + id + "');");
};
__DEXTUploadFLManager.prototype.createUploadMonitor = function(target, id, swfPath, bgColor, locale, theme, uiType, eventPostfixName) {
    var flashvars = {};
    
    flashvars.Locale		= !locale ? "" : locale;
    flashvars.Theme			= !theme ? "" : theme;
    flashvars.UIType		= !uiType ? "" : uiType;
    flashvars.AuthKey		= this.authKey; 
    flashvars.EventPostfixName = !eventPostfixName ? "" : eventPostfixName;
    
    var flashVarsStr = "";
    for (var varName in flashvars) {
    	flashVarsStr += varName + "=" + flashvars[varName] + "&";
    }

    var objectTag = "";
	
	if (isIE()) {
		objectTag = objectTag +
			"<object type='application/x-shockwave-flash'" +
			"classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000'" +
			"codebase='http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=10,3,0,0'" +
			"style='width:100%;height:100%' id='" + id + "'>" +
			"<param name='movie' value='" + swfPath + "' />" +
			"<param name='quality' value='high' />" +
			"<param name='bgcolor' value='" + bgColor + "' />" +
			"<param name='allowScriptAccess' value='always' />" +
			"<param name='allowFullScreen' value='true' />" +
			"<param name='allowNetworking' value='all' />" +
			"<param name='wmode' value='window' />" +
			"<param name='flashvars' value='" + flashVarsStr + "'/>";
	} else {
		objectTag = objectTag +
			"<object type='application/x-shockwave-flash' data='" + swfPath + "' style='width:100%;height:100%' id='" + id + "'>" +
			"<param name='movie' value='" + swfPath + "' />" +
			"<param name='quality' value='high' />" +
			"<param name='bgcolor' value='" + bgColor + "' />" +
			"<param name='allowScriptAccess' value='always' />" +
			"<param name='allowFullScreen' value='true' />" +
			"<param name='allowNetworking' value='all' />" +
			"<param name='wmode' value='window' /> " +
			"<param name='flashvars' value='" + flashVarsStr + "'/>" +
			"<param name='pluginurl' value='http://www.adobe.com/go/getflashplayer'>" +
			"<embed src='" + swfPath + "' quality='high' width='0' height='0' allowNetworking='all' allowScriptAccess='always' type='application/x-shockwave-flash' pluginspage='http://www.macromedia.com/go/getflashplayer' flashvars='" + flashVarsStr + "'/>";
	}
	
	objectTag = objectTag +
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
	
	document.getElementById(target).innerHTML = objectTag;
	this.upMonitors[id] = document.getElementById(id);
	eval("window." + id + " = document.getElementById('" + id + "');");
};
__DEXTUploadFLManager.prototype.createDownloadManager = function(target, id, swfPath, bgColor, wmode, locale, theme, downloadType, eventPostfixName) {
    var flashvars = {};
    
    flashvars.Locale		= !locale ? "" : locale;
    flashvars.Theme			= !theme ? "" : theme;
    flashvars.DownloadType	= !downloadType ? "" : downloadType ;
    flashvars.AuthKey		= this.authKey; 
    flashvars.EventPostfixName = !eventPostfixName ? "" : eventPostfixName;
    
    var flashVarsStr = "";
    for (var varName in flashvars) {
    	flashVarsStr += varName + "=" + flashvars[varName] + "&";
    }
    
    var objectTag = "";
	
	if (isIE()) {
		objectTag = objectTag +
			"<object type='application/x-shockwave-flash'" +
			"classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000'" +
			"codebase='http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=10,3,0,0'" +
			"style='width:100%;height:100%' id='" + id + "'>" +
			"<param name='movie' value='" + swfPath + "' />" +
			"<param name='quality' value='high' />" +
			"<param name='bgcolor' value='" + bgColor + "' />" +
			"<param name='allowScriptAccess' value='always' />" +
			"<param name='allowFullScreen' value='true' />" +		
			"<param name='allowNetworking' value='all' />" +			
			"<param name='wmode' value='" + wmode + "' />" +
			"<param name='flashvars' value='" + flashVarsStr + "'/>";
	} else {
		objectTag = objectTag +
			"<object type='application/x-shockwave-flash' data='" + swfPath + "' style='width:100%;height:100%' id='" + id + "'>" +
			"<param name='movie' value='" + swfPath + "' />" +
			"<param name='quality' value='high' />" +
			"<param name='bgcolor' value='" + bgColor + "' />" +
			"<param name='allowScriptAccess' value='always' />" +
			"<param name='allowFullScreen' value='true' />" +
			"<param name='allowNetworking' value='all' />" +
			"<param name='wmode' value='" + wmode + "' /> " +
			"<param name='flashvars' value='" + flashVarsStr + "'/>" +
			"<param name='pluginurl' value='http://www.adobe.com/go/getflashplayer'>" +
			"<embed src='" + swfPath + "' quality='high' width='0' height='0' allowNetworking='all' allowScriptAccess='always' type='application/x-shockwave-flash' pluginspage='http://www.macromedia.com/go/getflashplayer' flashvars='" + flashVarsStr + "'/>";
	}
	
	objectTag = objectTag +
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
	
	document.getElementById(target).innerHTML = objectTag;
	this.dwManagers[id] = document.getElementById(id);
	eval("window." + id + " = document.getElementById('" + id + "');");
};
__DEXTUploadFLManager.prototype.removeUploadMonitor = function (monitorId, containerId) {	
	if (this.upMonitors[monitorId]) { 
		this.upMonitors[monitorId] = null; 
		document.getElementById(containerId).innerHTML = ""; 
		eval("window." + monitorId + " = null;");
	} 
};
// Creating DEXTUploadFL
DEXTUploadFL = new __DEXTUploadFLManager();