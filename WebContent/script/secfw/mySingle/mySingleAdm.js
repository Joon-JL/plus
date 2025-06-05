var CHROME_BUG_AVOID = false;

///////////////////////////////////////////////////////
// -_-_-;;;;
var USE_EPSYNC = true;

// 윈도우즈고 IE가 아닌경우에는 중간 프로그램을 쓰겠다.
if(navigator.userAgent.toLowerCase().indexOf("windows")!=-1
		&& navigator.userAgent.toLowerCase().indexOf("msie")==-1)
{
	USE_EPSYNC = true; 
}else{
	USE_EPSYNC = false;  	
}


///////////////////////////////////////////////////////

var MYSINGLETRAY_VERSION = "1.5";
var MYSINGLETRAY_DOWNURL = "http://v6.samsung.net/portalWeb/cabs/SSO/dist.zip";
//var MYSINGLETRAY_DOWNURL = "/mysingletray/dist.zip";
var EPSYNC_VERSION = "1.2";
var EPSYNC_DOWNURL = "http://v6.samsung.net/portalWeb/cabs/Tray/";
//var EPSYNC_DOWNURL = "/mysingletray/";


////////////////////////////////////////////////////////

var MAX_TRY_COUNT_FOR_LOOKING_JAVA = 15; // 3 sec.
var DEBUG_WIDTH = "600";
var DEBUG_HEIGHT = "400";
var SSOCOOKIE = "SSOCookie";

////////////////////////////////////////////////////////
var Base64 = {

    // private property
    _keyStr : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",

    // public method for encoding
    encode : function (input) {
        var output = "";
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        var i = 0;

        input = Base64._utf8_encode(input);

        while (i < input.length) {

            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);

            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;

            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }

            output = output +
            this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
            this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);

        }

        return output;
    },

    // public method for decoding
    decode : function (input) {
        var output = "";
        var chr1, chr2, chr3;
        var enc1, enc2, enc3, enc4;
        var i = 0;

        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "") + "";
      
        while (i < input.length) {

            enc1 = this._keyStr.indexOf(input.charAt(i++));
            enc2 = this._keyStr.indexOf(input.charAt(i++));
            enc3 = this._keyStr.indexOf(input.charAt(i++));
            enc4 = this._keyStr.indexOf(input.charAt(i++));

            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;

            output = output + String.fromCharCode(chr1);

            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }

        }

        output = Base64._utf8_decode(output);

        return output;

    },

    // private method for UTF-8 encoding
    _utf8_encode : function (string) {
        string = string.replace(/\r\n/g,"\n") + "";
        var utftext = "";

        for (var n = 0; n < string.length; n++) {

            var c = string.charCodeAt(n);

            if (c < 128) {
                utftext += String.fromCharCode(c);
            }
            else if((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            }
            else {
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }

        }

        return utftext;
    },

    // private method for UTF-8 decoding
    _utf8_decode : function (utftext) {
        var string = "";
        var i = 0;
        var c = c1 = c2 = 0;

        while ( i < utftext.length ) {

            c = utftext.charCodeAt(i);

            if (c < 128) {
                string += String.fromCharCode(c);
                i++;
            }
            else if((c > 191) && (c < 224)) {
                c2 = utftext.charCodeAt(i+1);
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                i += 2;
            }
            else {
                c2 = utftext.charCodeAt(i+1);
                c3 = utftext.charCodeAt(i+2);
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                i += 3;
            }

        }

        return string;
    }

}

////////////////////////////////////////////////////////
function DynamicScript(fullUrl) {
    this.fullUrl = fullUrl; 
    this.noCache = '&' + noCache() ; 
    this.headLoc = document.getElementsByTagName("head").item(0);
    this.scriptId = 'JscriptId' + DynamicScript.scriptCounter++;
}

DynamicScript.scriptCounter = 1;

DynamicScript.prototype.buildScriptTag = function () {
    this.scriptObj = document.createElement("script");
    this.scriptObj.setAttribute("type", "text/javascript");
    this.scriptObj.setAttribute("charset", "utf-8");
    this.scriptObj.setAttribute("src", this.fullUrl + this.noCache);
    this.scriptObj.setAttribute("id", this.scriptId);
}
DynamicScript.prototype.noCache = function() {
    return 'noCache=' + (new Date()).getTime();
}
 
DynamicScript.prototype.removeScriptTag = function () {
    this.headLoc.removeChild(this.scriptObj);  
}

DynamicScript.prototype.addScriptTag = function () {
    this.headLoc.appendChild(this.scriptObj);
}

////////////////////////////////////////////////////////
var mySingleAdm = {
	pack : "",	
	
	receivepack : "",	
	
	version : "N/A",
	
	javaVersion : "N/A",
	
	useApplet : true,
	
	initialize : function() {
		if (this.useApplet ) {
			if (this.available()) {
				this.version = this.AdmJ.getVersion();
				this.javaVersion = this.AdmJ.getJavaVersion();
				return true;
			}
		}
		return false;
	},
	
	greaterOrEqualToJava16 : function() {
		if (this.useApplet) {
			return this.AdmJ.greaterOrEqualToJava16();
		} else {
			// flash it dosen't work!
			return true;
		}
	},
	
	// Caution: Don't use in Windows.
	existsJava16 : function() {
		if (this.useApplet) {
			return this.AdmJ.existsJavaCommandTxtFile();
		} else {
			return false;
		}
	},
	
	isIE : function() {
		browser=navigator.appName;
		return (browser.indexOf('Explorer') > 0);
	},
	
	isChrome : function() {
		browser=navigator.appVersion;
		return (browser.indexOf('Chrome') > 0);
	},

	update : function(version, downURL, showDialog) {
		
		if (this.AdmJ == null || this.useApplet == false ) return false;
		
		x = -1; y = -1;
		if (showDialog) {
			_top = window.screenTop != undefined ? window.screenTop : window.screenY;
			_left = window.screenLeft != undefined ? window.screenLeft : window.screenX;
			_width = ( document.documentElement.clientWidth ? document.documentElement.clientWidth : document.body.clientWidth );
			_height = ( document.documentElement.clientHeight ? document.documentElement.clientHeight : document.body.clientHeight );
			x = Math.round((_width / 2) + _left);
			y = Math.round((_height / 2) + _top);
		}
		
		return this.AdmJ.update(x, y, version, downURL);
	},
	
	addToPack : function(name,value) {
		if (name == null || value == null) return false;
		this.pack += name + "=" + encodeURIComponent(Base64.encode(value)) + "&";
		return true;
	},
	
	setSecureBox : function(securebox) {
		this.addToPack("securebox", securebox);
	},
	
	setBind : function(bind) {
		this.addToPack("bind", bind);
	},
	
	setTimeout : function(timeout) {
		this.addToPack("timeout", String(timeout));
	},
	
	setMode : function(mode) {
		this.addToPack("mode", String(mode));
	},
	
	setStartSSO : function(startsso) {
		this.addToPack("startsso", startsso);
	},
	
	setEndSSO : function(endsso) {
		this.addToPack("endsso", endsso);
	},
	
	setRegist : function(servicename, type, caption, endURL) {
		param = Base64.encode(servicename) + "," + Base64.encode(String(type)) + "," +
			Base64.encode(caption) + "," + Base64.encode(endURL);
		this.addToPack("regist", param);
	},
	
	setExecute : function(execute, type) {
		param = Base64.encode(execute) + "," + Base64.encode(String(type));
		this.addToPack("execute", param);
	},
	
	setLaterExecute : function(execute, type) {
		param = Base64.encode(execute) + "," + Base64.encode(String(type));
		this.addToPack("laterExecute", param);
	},
	
	setUserInfo : function(userinfo) {
		this.addToPack("userinfo", userinfo);
	},
	
	setIcode : function(icode) {
		this.addToPack("icode", icode);
	},
	
	setLocale : function(locale) {
		this.addToPack("locale", locale);
	},
	
	setEnroll : function(enroll) {
		this.addToPack("enroll", enroll);
	},
	
	setTrayVersion : function(version, downurl) {
		param = Base64.encode(version) + "," + Base64.encode(downurl);
		this.addToPack("trayversion", param);
	},
	
	setCheckTrayVersion : function(version) {
		this.addToPack("checktrayversion", version);
	},
	
	setLoginURL : function(url) {
		this.addToPack("setloginurl", url);
	},
	
	setDomain : function(domain) {
		this.addToPack("domain", domain);
	},

	setLC_DM : function(lc_dm) {
		this.addToPack("lc_dm", lc_dm);
	},
			
	sync : function(callback) {
		href = Base64.encode(window.location.href);
		random = "(" + Math.floor(Math.random()*1000) + ")";
		random = Base64.encode(random);
		
		this.receivepack = "N/A";
		this.reserveSync = callback;
		
		if (this.useApplet) {
			this.receivepack = this.AdmJ.sync(this.pack + "&random=" + random + "&href=" + href);
			
			if (callback != undefined) eval(callback + "();");
		} else {
			MySingleAdmFlash.sync(this.pack + "&random=" + random + "&href=" + href, "mySingleAdm.callbackSync");
		}
	},
	
	initFlash : function() {
		cookie = readCookieForSSO();
		
		if (cookie == null) {
			throw "[A] No Cookie!. Flash can't work!!";
		}
		
		idx = cookie.indexOf(":");
		username64 = cookie.substring(0, idx);
		portNumber = Base64.decode(cookie.substring(idx, cookie.length));
		
		if (USE_EPSYNC == false) {
			portNumber = 9131;
		}
		
		//alert('initFlash');
		
		MySingleAdmFlash.initialize(username64, portNumber);
	},
	
	islogin : function(callback) {
		this.receiveIslogin = "N/A";
		this.reserveIslogin = callback;
		if (this.useApplet) {
			this.receiveIslogin = this.AdmJ.islogin();
			if (callback != undefined) eval(callback + "(" + this.receiveIslogin + ");");
		} else {
			MySingleAdmFlash.islogin("mySingleAdm.callbackIslogin");
			return false;
		}
		return this.receiveIslogin;
	},
	
	logout : function(callback) {
		this.receiveLogout = "";
		this.reserveLogout = callback;
		if (this.useApplet) {
			this.AdmJ.logout();
			if (callback != undefined) eval(callback + "();");
		} else {
			MySingleAdmFlash.logout("mySingleAdm.callbackLogout");
		}
	},
	
	callbackSync : function(result) {
		this.receivepack = result;
		if (this.reserveSync != undefined && this.reserveSync != null) {
			eval(this.reserveSync + "();");
		}
		this.reserveSync = null;
	},
	
	callbackIslogin : function(result) {
		this.receiveIslogin = result;
		
		ret = false;
		
		if (result == "TRUE" || result == "true") {
			ret = true;
		}
		
		if (this.reserveIslogin != undefined && this.reserveIslogin != null) {
			eval(this.reserveIslogin+ "(" +  ret + ");");
		}
		this.reserveIslogin = null;
	},
	
	callbackLogout : function() {
		this.receiveLogout = "";
		deleteCookieForSSO();
		if (this.reserveLogout != undefined && this.reserveLogout != null) {
			eval(this.reserveLogout + "();");
		}
		this.reserveLogout = null;
	},
	
	execute : function(execute, type) {
		if (this.useApplet) {
			this.AdmJ.execute(execute, type);
		} else {
		}
	},

	executeEpFtp : function(execute, type, domainParam) {
		if (this.useApplet) {
			this.AdmJ.executeEpFtp(execute, type, domainParam);
		} else {
		}
	},

	stopEpFtp : function(execute, type) {
		if (this.useApplet) {
			this.AdmJ.stopEpFtp(execute, type);
		} else {
		}
	},
			
	runMySingleTray : function(sync) {
		if (this.useApplet) {
			if (sync == undefined) sync = false;
			this.AdmJ.runMySingleTray(sync);
			return true;
		} else {
			return false;
		}
	},
	
	useepsync : false,
	
	useEpAdmJC : function(use) {
		useepsync = use;
		if (this.useApplet) {
			this.AdmJ.setUseEpAdmJC(use);
			return true;
		} else {
			return false;
		}
	},
	
	readFromReceivePack : function(key) {
		if (this.receivepack == null || this.receivepack.length == 0 || key == null || key.length == 0) return "";
		
		key = key + "=";
		
		//for mac safari 
		this.receivepack = this.receivepack + "";
		
		fromIdx = this.receivepack.indexOf(key);
		
		if (fromIdx < 0)  return "";
		
		endIdx = this.receivepack.indexOf("&", fromIdx);
		if (endIdx < 0) endIdx = this.receivepack.length; 
		
		value = this.receivepack.substring(fromIdx + key.length, endIdx);
		
		return Base64.decode(value);
	},
	
	getSecureBox : function() {
		return this.readFromReceivePack("securebox");
	},
	
	getMode : function() {
		return this.readFromReceivePack("mode");
	},
	
	getBind : function() {
		return this.readFromReceivePack("bind");
	},
	
	getUserId : function() {
		return this.readFromReceivePack("userid");
	},
	
	getCachePath: function() {
		return this.readFromReceivePack("cachepath");
	},
	
	getStartSSO : function() {
		return this.readFromReceivePack("startsso");
	},
	
	getEndSSO : function() {
		return this.readFromReceivePack("endsso");
	},
	
	getTimeout : function() {
		return this.readFromReceivePack("timeout");
	},
	
	available : function() {
		if (this.useApplet) {
			if (this.AdmJ == null) return false;
			
			if (this.isIE()) {
				return this.AdmJ != null;
			} else {
				return (this.AdmJ != undefined && this.AdmJ != null && this.AdmJ.init != undefined && this.AdmJ.init != null);
			}
		} else {
			
			ret = readCookieForSSO() != null && MySingleAdmFlash.initialize != undefined && !isFlashHealerRunning();
			
			if (ret == true) {
				this.initFlash();
			}
			return ret;
		}
	},
	
	changeHeader : function() {
		this.originalTitle = window.document.title;    
	    random = "(" + Math.floor(Math.random()*1000) + ")";
	    title = this.originalTitle + random;
	    window.document.title = title;
	    return random;
	},	
	
	getBackToOriginalHeader : function() {
	    window.document.title = this.originalTitle; 
	},
	
	// deprecated
	// return true | false | undefined
	isEpTrayAlive : function(callback) {
		image = new Image();
		image.onload = function() {
			eval(callback + "(true)");
		};
		image.onerror = function() {
			eval(callback + "(false)");
		};
		image.src="http://localhost:9131/image";
	},
	
	isAlive : function() {
		if (this.useApplet) {
			return this.AdmJ.isAlive();
		} else {
			// TODO: 
			return true;
		}
	},
	
	isActive : function() {
		return this.AdmJ.isActive();
	},
	
	checkEpSync : function(version, baseURL) {
		if (this.useApplet) {
			return this.AdmJ.checkEpSync(version, baseURL);
		}
	},
	
	onload : function(code, count) {
		
		if (count == undefined) {
			count = 0;
		}
		
		if (this.useApplet) {
			this.AdmJ = document.mySingleAdmApplet;
		} else {
			if (isFlashHealerRunning()) {
				count = 0;
			}
		}
		
		if (this.available() || count >= MAX_TRY_COUNT_FOR_LOOKING_JAVA) {
			eval(code);
		} else {
			count = count + 1;
			setTimeout("mySingleAdm.onload('" + code + "', " + count + ");", 200);
		}
	},
	
	// deprecated. because in this case, we don't use liveconnect skill anymore.
	onloadInChrome : function(code, count) {
		try {
			
			if (count == 0) {
				count++;
				// wait for applet loading.
				setTimeout("mySingleAdm.onloadInChrome('" + code + "', " + count + ");", 1000);
				return;
			} else {
				if (this.available()) {
					eval(code);
					return;
				} else {
					throw "Chrome Bug occured";
					/*
					// This is chrome there is no way to escapse.
					eval(code);
					return;
					*/
				}
			}
			
		} catch (err) {
			alert("Chome Bug ocurred. window will be reloaded : " + err.description);
			window.location.reload();
		}
	},
	
	addAppletTag : function(verataller, mainclass, debug) {
		if (verataller == undefined) {
			verataller = "verataller.jar";
		}
		
		if (debug == undefined) {
			debug = false;
		}
		
		width = "0";
		height = "0";
		
		if (debug) {
			width = DEBUG_WIDTH;
			height = DEBUG_HEIGHT;
		}
		
		_top = window.screenTop != undefined ? window.screenTop : window.screenY;
		_left = window.screenLeft != undefined ? window.screenLeft : window.screenX;
		_width = ( document.documentElement.clientWidth ? document.documentElement.clientWidth : document.body.clientWidth );
		_height = ( document.documentElement.clientHeight ? document.documentElement.clientHeight : document.body.clientHeight );
		x = Math.round((_width / 2) + _left);
		y = Math.round((_height / 2) + _top);
		
		appletTag = 
			'<applet mayscript="true" name="mySingleAdmApplet" ' +
			'	code="' + mainclass + '" ' +
			'	width="' + width + '" ' +
			'	height="'+ height + '" ' +
			'	archive="'  + verataller +  '"> ' + 
			'	<param name=debug VALUE="' + debug + '">' +
			'	<param name=x VALUE="' + x + '">' +
			'	<param name=y VALUE="' + y + '">' +
			'	<param name=useEpSync VALUE="' + USE_EPSYNC+ '">' +
			'	<param name=archiveURL VALUE="' + verataller + '">' +
			'	<param name=version VALUE="' + MYSINGLETRAY_VERSION + '">' +
			'	<param name=downURL VALUE="' + MYSINGLETRAY_DOWNURL + '">' +
			'	<param name=epsyncVersion VALUE="' + EPSYNC_VERSION + '">' +
			'	<param name=epsyncDownURL VALUE="' + EPSYNC_DOWNURL + '">' +
			'</applet>';
		
		/*
		embedTag = 
			'<embed type="application/x-java-applet;version=1.6"  \n' +
			'   name="mySingleAdmApplet" \n' +
			'   width="' + width + '" \n' +
			'   height="'+ height + '" \n' +
			'   code="com.wizvera.sso.samsung.webapi.MySingleAdm.class" \n' +
			'   mayscript=true \n' +
			'   archive="' + verataller + '" \n' +
			'   pluginspage="http://java.sun.com/products/plugin/1.3/plugin-install.html"> \n' +
			'   <parameter name="archive" value="verataller.jar"> \n' +
			'</embed> \n' ;
		//outputTag = embedTag;
		*/
		
		
		outputTag = appletTag;
		//alert(outputTag);
		
		if (debug) document.writeln("<div>");
		document.writeln(outputTag);
		if (debug) document.writeln("</div>");
	},
	
	// OS detection
	isWindows : function() {
		if (navigator.appVersion.indexOf("Win")!=-1) return true;
		return false;
	},
	
	isMac : function() {
		if (navigator.appVersion.indexOf("Mac")!=-1) return true;
		return false;
	},
	
	getUserName : function() {
		return this.AdmJ.getUserName();
	},
	
	getPortNumber : function() {
		return this.AdmJ.getPortNumber();
	},
	
	addFlashTag : function(flash, debug) {
		if (flash == undefined) {
			flash = "verataller";
		} else {
			flash = flash.replace(/\.swf$/i, "");
		}
		
		if (debug == undefined) {
			debug = false;
		}
		
		if (debug) document.write("<div>");
		addMySingleAdmFlash(flash, debug);
		if (debug) document.write("</div>");
	},
		
	installComponent : function (callbackInit, path, debug) {

		try {
			
			if (this.isIE()) {
				eval(callbackInit);
				return;
			} 
			
			//document.body.style.cursor="wait";
			
			this.callbackInit = callbackInit;

			if (debug == undefined) {
				debug = false;
			}

			if (path == undefined) {
				path = "";
			} 

			if (this.isChrome() && CHROME_BUG_AVOID) {
				
				this.useApplet = false;
				if (readCookieForSSO() == null) {
					// FlashHelper need.
					this.addAppletTag( path + "verataller.jar", 'com.wizvera.sso.samsung.webapi.FlashHelper.class');
				}
				this.addFlashTag( path + "verataller.swf", debug);
			} else {

				this.useApplet = true;
				this.addAppletTag( path + "verataller.jar", 'com.wizvera.sso.samsung.webapi.MySingleAdm.class', debug);
				
				this.AdmJ = document.mySingleAdmApplet;
				
			}
			this.installComponentPart2();
			
		} catch (err) {
			alert("InstallComponent : " + err);
		} 
		
	},

	isOpera : function() {
		browser=navigator.appName;
		return (browser.toLowerCase().indexOf('opera') != -1);
	}, 
			
	installComponentPart2 : function () {
		try {
			if (this.useApplet) {
				if(this.isOpera()){
						setTimeout("mySingleAdm.onload('" + this.callbackInit + "');", 10);
				}else{
					this.onload(this.callbackInit);
				}	
			} else {
				
				if (MySingleAdmFlash.initialize == undefined) {
					delaytime = 200;
					setTimeout("mySingleAdm.installComponentPart2();", delaytime);
					return;
				}
				
				this.onload(this.callbackInit);
			}
			
			//document.body.style.cursor="default";
		} catch (err) {
			alert("InstallComponentPart2 33: " + err);
		}
	}
	
};

function addMySingleAdmFlash(verataller, debug) {
	
	width = "1";
	height = "1";
	
	if (debug) {
		width = DEBUG_WIDTH;
		height = DEBUG_HEIGHT;
	}
	
	/*
	document.write('<object id="MySingleAdmFlash2" ');
	document.write('  name="MySingleAdmFlash2" ');
	document.write('  width="' + width + ' " ');
	document.write('  height="' + height + '" ');
	document.write('  align="middle" ');
	document.write('  codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0" ');
	document.write('  classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000">');
	document.write('  <param value="' + verataller + '.swf" name="movie"/>');
	document.write('  <param value="always" name="allowScriptAccess"/>');
	document.write('  <param value="transparent" name="wmode"/>');
	document.write('  <param value="false" name="menu"/>');
	*/
	
	document.write('  <embed ');
	document.write('    src="' + verataller +'.swf" ');
	document.write('    width="' + width + '" ');
	document.write('    height="' + height + '" ');
	document.write('    align="middle" ');
	document.write('    id="MySingleAdmFlash" ');
	document.write('    quality="high" ');
	document.write('    name="MySingleAdmFlash" ');
	document.write('    allowscriptaccess="always" ');
	document.write('    pluginspage="http://www.macromedia.com/go/getflashplayer" ');
	document.write('    type="application/x-shockwave-flash" ');
	document.write('    />');
	
	//document.write('</object>');
	
}

//From Flex DevelopmentTool
function addMySingleAdmFlash2(verataller, debug) {
	//-----------------------------------------------------------------------------
	//Globals
	//Major version of Flash required
	var requiredMajorVersion = 9;
	//Minor version of Flash required
	var requiredMinorVersion = 0;
	//Minor version of Flash required
	var requiredRevision = 0;
	//Version check for the Flash Player that has the ability to start Player Product Install (6.0r65)

	var hasProductInstall = DetectFlashVer(6, 0, 65);

//Version check based upon the values defined in globals
	var hasRequestedVersion = DetectFlashVer(requiredMajorVersion, requiredMinorVersion, requiredRevision);	
	
	width = "1";
	height = "1";
	
	if (debug) {
		width = DEBUG_WIDTH;
		height = DEBUG_HEIGHT;
	}
	
	if ( hasProductInstall && !hasRequestedVersion ) {
		// DO NOT MODIFY THE FOLLOWING FOUR LINES
		// Location visited after installation is complete if installation is required
		var MMPlayerType = (isIE == true) ? "ActiveX" : "PlugIn";
		var MMredirectURL = window.location;
		document.title = document.title.slice(0, 47) + " - Flash Player Installation";
		var MMdoctitle = document.title;
		AC_FL_RunContent(
				"src", "playerProductInstall",
				"FlashVars", "MMredirectURL="+MMredirectURL+'&MMplayerType='+MMPlayerType+'&MMdoctitle='+MMdoctitle+"",
				"width", "300",
				"height", "300",
				"align", "middle",
				"id", verataller,
				"quality", "high",
				"bgcolor", "#869ca7",
				"name", "MySingleAdmFlash",
				"allowScriptAccess","sameDomain",
				"type", "application/x-shockwave-flash",
				"pluginspage", "http://www.adobe.com/go/getflashplayer"
				);
	} else if (hasRequestedVersion) {
		// if we've detected an acceptable version
		// embed the Flash Content SWF when all tests are passed

		AC_FL_RunContent(
				"src", verataller,
				"width", width,
				"height", height,
				"align", "middle",
				"id", "MySingleAdmFlash",
				"quality", "high",
				"bgcolor", "#869ca7",
				"name", "MySingleAdmFlash",
				"allowScriptAccess","sameDomain",
				"type", "application/x-shockwave-flash",
				"pluginspage", "http://www.adobe.com/go/getflashplayer"
				);
	} else {  // flash is too old or we can't detect the plugin
		AC_FL_RunContent(
				"src", verataller,
				"width", width,
				"height", height,
				"align", "middle",
				"id", "MySingleAdmFlash",
				"quality", "high",
				"bgcolor", "#869ca7",
				"name", "MySingleAdmFlash",
				"allowScriptAccess","sameDomain",
				"type", "application/x-shockwave-flash",
				"pluginspage", "http://www.adobe.com/go/getflashplayer"
				);
		/*
		var alternateContent = 'Alternate HTML content should be placed here. '
			+ 'This content requires the Adobe Flash Player. '
			+ '<a href=http://www.adobe.com/go/getflash/>Get Flash</a>';
		document.write(alternateContent);  // insert non-flash content
		*/
	}

}

////////////////////////////////////////////////////////////////////////////


function isFlashHealerRunning() {
	value = readCookie("FlashHelper");
	return value != null;
}

function readCookieForSSO() {
	return readCookie(SSOCOOKIE);
}

function deleteCookieForSSO()
{
	var cookie_date = new Date ( );  // current date & time
	cookie_date.setTime ( cookie_date.getTime() - 1 );
	document.cookie = SSOCOOKIE += "=; expires=" + cookie_date.toGMTString();
}

function readCookie(cookiename) {
	var nameEQ = cookiename + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}

function isIE() {
	browser=navigator.appName;
	return (browser.indexOf('Explorer') > 0);
}

