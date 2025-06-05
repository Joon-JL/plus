/* show/hide layer */
function showHideLayer() { 
var i,p,v,obj,args=showHideLayer.arguments;
for (i=0; i<(args.length-2); i+=3) if ((obj=MM_findObj(args[i]))!=null) { v=args[i+2];
if (obj.style) { obj=obj.style; v=(v=='show')?'block':(v='hide')?'none':v; }
obj.display=v; }
}

/* toggle */
function toggleLayer(whichLayer) {
  var elem, vis;
  if(document.getElementById) 
    elem = document.getElementById(whichLayer);
  else if(document.all) 
      elem = document.all[whichLayer];
  else if(document.layers) 
    elem = document.layers[whichLayer];
  vis = elem.style;
 
  if(vis.display==''&&elem.offsetWidth!=undefined&&elem.offsetHeight!=undefined)
    vis.display = (elem.offsetWidth!=0&&elem.offsetHeight!=0)?'block':'none';
  vis.display = (vis.display==''||vis.display=='block')?'none':'block';
}

 function menu_Toggle_m(imgCtr, divName)
		{		
			if(document.getElementById(divName).style.display == "none")
			{
				document.getElementById(divName).style.display = "block";
				imgCtr.src = "../../images/clms/ko/icon/ico_collapse.png";
				imgCtr.title = "collapse";
			}
			else
			{
				document.getElementById(divName).style.display = "none" ;
				imgCtr.src = "../../images/clms/ko/icon/ico_expand.png";	
				imgCtr.title = "expand";
			}				
}

 function search_Toggle(imgCtr, divName)
		{		
			if(document.getElementById(divName).style.display == "block")
			{
				document.getElementById(divName).style.display = "none";
				imgCtr.src = "../images/btn/btn_search_detail_expand.gif";
				imgCtr.onMouseOver = "../images/btn/btn_search_detail_expand_on.gif";
				imgCtr.title = "show";
			}
			else
			{
				document.getElementById(divName).style.display = "block" ;
				imgCtr.src = "../images/btn/btn_search_detail_collapse.gif";	
				imgCtr.onMouseOver = "../images/btn/btn_search_detail_collapse_on.gif";
				imgCtr.title = "hide";
			}				
}




/* popup */
function open_window(name, url, left, top, width, height, toolbar, menubar, statusbar, scrollbar, resizable)
{
  toolbar_str = toolbar ? 'yes' : 'no';
  menubar_str = menubar ? 'yes' : 'no';
  statusbar_str = statusbar ? 'yes' : 'no';
  scrollbar_str = scrollbar ? 'yes' : 'no';
  resizable_str = resizable ? 'yes' : 'no';
  window.open(url, name, 'left='+left+',top='+top+',width='+width+',height='+height+',toolbar='+toolbar_str+',menubar='+menubar_str+',status='+statusbar_str+',scrollbars='+scrollbar_str+',resizable='+resizable_str);
}

/* popup center */
function MoveCenterWindow(){
  var cx = document.body.clientWidth;
  var cy = document.body.clientHeight;
  var sw = screen.availWidth;
  var sh = screen.availHeight;
  var px = (sw-cx)/2;
  var py = (sh-cy)/2
  window.moveTo(px, py);
}
//MoveCenterWindow();
// 팝업창 중앙으로 위치 
function center(){ 
    var x,y; 
    if (self.innerHeight) { // IE 외 모든 브라우저 
        x = (screen.availWidth - self.innerWidth) / 2; 
        y = (screen.availHeight - self.innerHeight) / 2; 
    }else if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict 모드 
        x = (screen.availWidth - document.documentElement.clientWidth) / 2; 
        y = (screen.availHeight - document.documentElement.clientHeight) / 2; 
    }else if (document.body) { // 다른 IE 브라우저( IE < 6) 
        x = (screen.availWidth - document.body.clientWidth) / 2; 
        y = (screen.availHeight - document.body.clientHeight) / 2; 
    } 
    window.moveTo(x,y); 
} 

/* Image Roll Over */
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

/* Toggle */
function fn_ToggleDetail(imgCtr, divName)
		{		
			if(document.getElementById(divName).style.display == "block")
			{
				document.getElementById(divName).style.display = "none";
				imgCtr.src = "../images/nGSBN/ico/plus.gif";				
			}
			else
			{
				document.getElementById(divName).style.display = "block" ;
				imgCtr.src = "../images/nGSBN/ico/minus.gif";			
			}				
		} 
/* Popup close/open */
function go(url){
	opener.location.href = url;
	self.close();

}

function setCookie( name, value, expiredays )
{
	var todayDate = new Date();
	todayDate.setDate( todayDate.getDate() + expiredays );
	document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";";
}

function getCookie(name) {
	var Found = false;
	var start, end;
	var i = 0;

	while(i <= document.cookie.length) {
		start = i;
		end = start + name.length;

		if(document.cookie.substring(start, end) == name) {
			Found = true;
			break;
		}

		i++;
	}

	if(Found == true) {
		start = end + 1;
		end = document.cookie.indexOf(";", start);
		if(end < start)
			end = document.cookie.length;
		return document.cookie.substring(start, end);
	}
	return "";
}
function ie_close() 
{ 
// 		setCookie( "ie_event", "ie_event" , 1); 
}

/* Png image */

function setPng24(obj) {
        obj.width=obj.height=1;
        obj.className=obj.className.replace(/\bpng24\b/i,'');

        obj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+ obj.src +"',sizingMethod='image');";
        obj.src=''; 
        return '';
    }

/* input text */

function text_form()
{

 var f = document.form;  // form = <body>안의 폼이름

 if(f.text_field.value == f.text_field.defaultValue) { f.text_field.value = ""; }

}

/* PrototypeTest */
function tal_Toggle(imgCtr, divName)
{		
	if(document.getElementById(divName).style.display == "block")
	{
		document.getElementById(divName).style.display = "none";
		imgCtr.src = "/images/clm/ko/icon/ico_plus.gif";
		imgCtr.onMouseOver = "/images/clm/ko/icon/ico_minus.gif";	
		imgCtr.title = "show";
	}
	else
	{
		document.getElementById(divName).style.display = "block" ;
		imgCtr.src = "/images/clm/ko/icon/ico_minus.gif";	
		imgCtr.onMouseOver = "/images/clm/ko/icon/ico_plus.gif";	
		imgCtr.title = "hide";
	}				
}
function tit_Toggle(imgCtr, divName)
{
	if(document.getElementById(divName).style.display=="" || document.getElementById(divName).style.display == "block")
	{
		document.getElementById(divName).style.display = "none";
		imgCtr.src = "/images/clm/ko/btn/btn_down.gif";
		imgCtr.onMouseOver = "/images/clm/ko/btn/btn_up.gif";	
		imgCtr.title = "show";
	}
	else
	{
		//document.getElementById(divName).style.display = "" ;
		$('#'+divName).attr("style","display:"); //지불계획
		imgCtr.src = "/images/clm/ko/btn/btn_up.gif";
		imgCtr.onMouseOver = "/images/clm/ko/btn/btn_down.gif";	
		imgCtr.title = "hide";
	}				
}
function tit_Toggle_s(imgCtr, divName)
{
	if(document.getElementById(divName).style.display=="" || document.getElementById(divName).style.display == "block")
	{
		document.getElementById(divName).style.display = "none";
		imgCtr.src = "/images/clm/ko/btn/btn_s_down.gif";
		imgCtr.onMouseOver = "/images/clm/ko/btn/btn_s_up.gif";	
		imgCtr.title = "show";
	}
	else
	{
		document.getElementById(divName).style.display = "block" ;
		imgCtr.src = "/images/clm/ko/btn/btn_s_up.gif";
		imgCtr.onMouseOver = "/images/clm/ko/btn/btn_s_down.gif";	
		imgCtr.title = "hide";
	}				
}




    var tipwidth='150px'; //툴팁 가로사이즈
	var tipbgcolor='#FCFCFC';  //툴팁배경색상
	var disappeardelay=100;  //마우스아웃시 사라지는 시간설정
	var vertical_offset="6px"; //기준점에서 하단으로 여백
	var horizontal_offset="6px"; //기준점에서 좌측 여백


	var ie4=document.all;
	var ns6=document.getElementById&&!document.all;

	if (ie4||ns6)
		document.write('<div id="fixedtipdiv" style="display:none;width:'+tipwidth+';background-color:'+tipbgcolor+'" ></div>');

	function getposOffset(what, offsettype){
		var totaloffset=(offsettype=="left")? what.offsetLeft : what.offsetTop;
		var parentEl=what.offsetParent;
		while (parentEl!=null){
			totaloffset=(offsettype=="left")? totaloffset+parentEl.offsetLeft : totaloffset+parentEl.offsetTop;
			parentEl=parentEl.offsetParent;
		}
	return totaloffset;
	}


	function showhide(obj, e, visible, hidden, tipwidth){
		if (ie4||ns6)
			dropmenuobj.style.left=dropmenuobj.style.top=-500;
		if (tipwidth!=""){
			dropmenuobj.widthobj=dropmenuobj.style;
			dropmenuobj.widthobj.width=tipwidth;
		}
		if (e.type=="click" && obj.visibility==hidden || e.type=="mouseover")
			obj.visibility=visible;
		else if (e.type=="click")
			obj.visibility=hidden;
	}

	function iecompattest(){
		return (document.compatMode && document.compatMode!="BackCompat")? document.documentElement : document.body;
	}

	function clearbrowseredge(obj, whichedge){
		var edgeoffset=(whichedge=="rightedge")? parseInt(horizontal_offset)*-1 : parseInt(vertical_offset)*-1;
		if (whichedge=="rightedge"){
		var windowedge=ie4 && !window.opera? iecompattest().scrollLeft+iecompattest().clientWidth-20 : window.pageXOffset+window.innerWidth-15;
			dropmenuobj.contentmeasure=dropmenuobj.offsetWidth;
		if (windowedge-dropmenuobj.x < dropmenuobj.contentmeasure);
			edgeoffset=dropmenuobj.contentmeasure-obj.offsetWidth;
		}
		else{
		var windowedge=ie4 && !window.opera? iecompattest().scrollTop+iecompattest().clientHeight-20 : window.pageYOffset+window.innerHeight-18;
			dropmenuobj.contentmeasure=dropmenuobj.offsetHeight;
		if (windowedge-dropmenuobj.y < dropmenuobj.contentmeasure)
			edgeoffset=dropmenuobj.contentmeasure+obj.offsetHeight;
		}
	return edgeoffset;
	}

	function fixedtooltip(menucontents, obj, e, tipwidth){
		
		if (window.event) event.cancelBubble=true;
		else if (e.stopPropagation) e.stopPropagation();
		
		    clearhidetip();
		    dropmenuobj=document.getElementById? document.getElementById("fixedtipdiv") : fixedtipdiv;
			dropmenuobj.innerHTML=menucontents;

		if (ie4||ns6){
			showhide(dropmenuobj.style, e, "visible", "hidden", tipwidth);
			dropmenuobj.x=getposOffset(obj, "left");
			dropmenuobj.y=getposOffset(obj, "top");
			dropmenuobj.style.left=dropmenuobj.x-clearbrowseredge(obj, "rightedge")+"px";
			dropmenuobj.style.top=dropmenuobj.y-clearbrowseredge(obj, "bottomedge")+obj.offsetHeight+"px";
			
		}
	}

	function hidetip(e){
		if (typeof dropmenuobj!="undefined"){
		if (ie4||ns6)
			dropmenuobj.style.visibility="hidden";
		}
	}

	function delayhidetip(){
		if (ie4||ns6)
			delayhide=setTimeout("hidetip()",disappeardelay);
	}

	function clearhidetip(){
		if (typeof delayhide!="undefined")
			clearTimeout(delayhide);
	}


