// Javascript Document 
// CPIS ( Compliance Program Information System )
// moon sunjeong 2010/05/11





// div hidden, show start
function findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function showHideLayers() { //v6.0
  var i,p,v,obj,args=showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3) if ((obj=findObj(args[i]))!=null) { v=args[i+2];
    if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v=='hide')?'hidden':v; }
    obj.visibility=v; }
}
// div hidden, show end



// tree menu start
var oldBook = '';
function bookClick( treeBook )
{
  if( oldBook != treeBook ) {
    if( oldBook !='' ) {
      oldBook.style.display = 'none';
    }
    treeBook.style.display = 'block';
    oldBook = treeBook;

  } else {
    treeBook.style.display = 'none';
    oldBook = '';
  }
}

var oldNote = '';
var oldImg = '';
function noteClick( treeNote , obj )
{
  if( oldNote != treeNote , oldImg != obj ) {
    if( oldNote !='' ) {
      oldNote.style.display = 'none';
      oldImg.className = 'bookFont';
    }
    treeNote.style.display = 'block';
    obj.className = 'bookFont2';
    oldNote = treeNote;
    oldImg = obj;

  } else {
    treeNote.style.display = 'none';
    obj.className = 'bookFont';
    oldNote = '';
    oldImg = '';
  }
}
// tree menu end



// frame size start
function frameSize(sizeValue) {
	if (sizeValue == 'S') {
		parent.frMnaLeft.cols = '17,*';
	}
	else if (sizeValue == 'L') {
		parent.frMnaLeft.cols = '280,*';
	}
}

function frameSizeC(sizeValueC) {
	if (sizeValueC == 'S') {
		parent.frComLeft.cols = '17,*';
	}
	else if (sizeValueC == 'L') {
		parent.frComLeft.cols = '220,*';
	}
}

function frameSizeT(sizeValueT) {
	if (sizeValueT == 'S') {
		top.frTop.rows = '17,*';
	}
	else if (sizeValueT == 'L') {
		top.frTop.rows = '104,*';
	}
}

function divShow(obj) {
  obj.style.display='block';
}
function divHidden(obj) {
  obj.style.display='none';
}
// frame size end





// tree note color start   background:#3aafe4; color:#ffffff
var noteFont = '';
function noteDown(obj){
  if( noteFont != obj ) {
	if( noteFont !='' ) {
	   noteFont.className = 'noteFontDeco';
	}
		obj.className='noteFont';
		noteFont = obj;
	} else {
		obj.className='deco';
		noteFont = '';
  }
}
// tree note color end




// left menu start
var oldMenu = '';
function menuClick( subMenu )
{
  if( oldMenu != subMenu ) {
    if( oldMenu !='' ) {
      oldMenu.style.display = 'none';
    }
    subMenu.style.display = 'block';
    oldMenu = subMenu;

  } else {
    subMenu.style.display = 'none';
    oldMenu = '';
  }
}
// left menu end



// tr over color start 
function trOverColor(obj) {
  obj.style.background='#f4f4f4'
}
function trOutColor(obj) {
  obj.style.background='#ffffff'
}
// tr over color end





// mna top tip div start 
function mnaTip(flag){
	if(flag == "01"){
		tagDiv.style.display="block";
		detailDiv.style.display="none";
		fileDownDiv.style.display="none";
	}else if(flag == "02"){
		tagDiv.style.display="none";
		detailDiv.style.display="block";
		fileDownDiv.style.display="none";
	}else if(flag == "03"){
		tagDiv.style.display="none";
		detailDiv.style.display="none";
		fileDownDiv.style.display="block";
	}
}
// mna top tip div end 



// compliance Desk left title img start
function comTtitleImg(flag){
	if(flag == "01"){
		leftTitle01.style.display="block";
		leftTitle02.style.display="none";
		leftTitle03.style.display="none";
		leftTitle04.style.display="none";
		leftTitle05.style.display="none";
		leftTitle06.style.display="none";
	}else if(flag == "02"){
		leftTitle01.style.display="none";
		leftTitle02.style.display="block";
		leftTitle03.style.display="none";
		leftTitle04.style.display="none";
		leftTitle05.style.display="none";
		leftTitle06.style.display="none";
	}else if(flag == "03"){
		leftTitle01.style.display="none";
		leftTitle02.style.display="none";
		leftTitle03.style.display="block";
		leftTitle04.style.display="none";
		leftTitle05.style.display="none";
		leftTitle06.style.display="none";
	}else if(flag == "04"){
		leftTitle01.style.display="none";
		leftTitle02.style.display="none";
		leftTitle03.style.display="none";
		leftTitle04.style.display="block";
		leftTitle05.style.display="none";
		leftTitle06.style.display="none";
	}else if(flag == "05"){
		leftTitle01.style.display="none";
		leftTitle02.style.display="none";
		leftTitle03.style.display="none";
		leftTitle04.style.display="none";
		leftTitle05.style.display="block";
		leftTitle06.style.display="none";
	}else if(flag == "06"){
		leftTitle01.style.display="none";
		leftTitle02.style.display="none";
		leftTitle03.style.display="none";
		leftTitle04.style.display="none";
		leftTitle05.style.display="none";
		leftTitle06.style.display="block";
	}
}
// compliance Desk left title img end



// Admin left title img start
function admTtitleImg(flag){
	if(flag == "01"){
		leftTitle01.style.display="block";
		leftTitle02.style.display="none";
		leftTitle03.style.display="none";
		leftTitle04.style.display="none";
		leftTitle05.style.display="none";
		leftTitle06.style.display="none";
	}else if(flag == "02"){
		leftTitle01.style.display="none";
		leftTitle02.style.display="block";
		leftTitle03.style.display="none";
		leftTitle04.style.display="none";
		leftTitle05.style.display="none";
		leftTitle06.style.display="none";
	}else if(flag == "03"){
		leftTitle01.style.display="none";
		leftTitle02.style.display="none";
		leftTitle03.style.display="block";
		leftTitle04.style.display="none";
		leftTitle05.style.display="none";
		leftTitle06.style.display="none";
	}else if(flag == "04"){
		leftTitle01.style.display="none";
		leftTitle02.style.display="none";
		leftTitle03.style.display="none";
		leftTitle04.style.display="block";
		leftTitle05.style.display="none";
		leftTitle06.style.display="none";
	}else if(flag == "05"){
		leftTitle01.style.display="none";
		leftTitle02.style.display="none";
		leftTitle03.style.display="none";
		leftTitle04.style.display="none";
		leftTitle05.style.display="block";
		leftTitle06.style.display="none";
	}else if(flag == "06"){
		leftTitle01.style.display="none";
		leftTitle02.style.display="none";
		leftTitle03.style.display="none";
		leftTitle04.style.display="none";
		leftTitle05.style.display="none";
		leftTitle06.style.display="block";
	}
}
// Admin left title img end


// About CP left title img start
function abtTtitleImg(flag){
	if(flag == "01"){
		leftTitle01.style.display="block";
		leftTitle02.style.display="none";
		leftTitle03.style.display="none";
		leftTitle04.style.display="none";
	}else if(flag == "02"){
		leftTitle01.style.display="none";
		leftTitle02.style.display="block";
		leftTitle03.style.display="none";
		leftTitle04.style.display="none";
	}else if(flag == "03"){
		leftTitle01.style.display="none";
		leftTitle02.style.display="none";
		leftTitle03.style.display="block";
		leftTitle04.style.display="none";
	}else if(flag == "04"){
		leftTitle01.style.display="none";
		leftTitle02.style.display="none";
		leftTitle03.style.display="none";
		leftTitle04.style.display="block";
	}
}
// About CP left title img end

// Manuals left title img start
function mnTtitleImg(flag){
	if(flag == "01"){
		leftTitle01.style.display="block";
	}
}
// Manuals left title img end




//-------------------------------------------------------------------
//PopUp Window Open �Լ�.
//-------------------------------------------------------------------
var PopUpWindow;
function PopUpWindowOpen(surl, popupwidth, popupheight, bScroll)
{
	if( popupwidth  > window.screen.width )
		popupwidth = window.screen.width;
	if( popupheight > window.screen.height )
		popupheight = window.screen.height; 
		
	if( isNaN(parseInt(popupwidth)) ){
		Top  = (window.screen.availHeight - 600) / 2;
		Left = (window.screen.availWidth  - 800) / 2;
	} else {
		Top  = (window.screen.availHeight - popupheight)  / 2;
		Left = (window.screen.availWidth  - popupwidth) / 2;
	}

	if (Top < 0) Top = 0;
	if (Left < 0) Left = 0;
	
	popupwidth = parseInt(popupwidth) + 10 ;
	popupheight = parseInt(popupheight) + 30 ;
	
	Future = "fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;

	PopUpWindow = window.open(surl, "PopUpWindow" , Future)
	PopUpWindow.resizeTo(parseInt(popupwidth)+10, parseInt(popupheight)+30);
	PopUpWindow.focus();
	
}//" + (bScroll ? "yes" : "no") + "

function PopUpWindowOpenTarget(surl, popupwidth, popupheight, bScroll,target)
{
	if( popupwidth  > window.screen.width )
		popupwidth = window.screen.width;
	if( popupheight > window.screen.height )
		popupheight = window.screen.height; 
		
	if( isNaN(parseInt(popupwidth)) ){
		Top  = (window.screen.availHeight - 600) / 2;
		Left = (window.screen.availWidth  - 800) / 2;
	} else {
		Top  = (window.screen.availHeight - popupheight)  / 2;
		Left = (window.screen.availWidth  - popupwidth) / 2;
	}

	if (Top < 0) Top = 0;
	if (Left < 0) Left = 0;
	
	popupwidth = parseInt(popupwidth) + 10 ;
	popupheight = parseInt(popupheight) + 30 ;
	
	Future = "fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;

	PopUpWindow = window.open(surl, target , Future)
	PopUpWindow.resizeTo(parseInt(popupwidth)+10, parseInt(popupheight)+30);
	PopUpWindow.focus();
	
}//" + (bScroll ? "yes" : "no") + "




//-------------------------------------------------------------------
//Ask Questions Category Layer �Լ�.
//-------------------------------------------------------------------

/** #### ���̾�̱� #### **/
function showLayer() {
	document.all["askqCatRound"].style.display = "block";
	document.all["askqCat"].style.display = "block";
}
/** #### ���̾���� #### **/
function hiddenLayer() {
	document.all["askqCatRound"].style.display = "none";
	document.all["askqCat"].style.display = "none";
}




// sub DetailSearch div start 
function mnaAdvSch(flag){
	if(flag == "01"){
		detailSchDiv.style.display="block";
	}
}
// subDetailSearch div end 

// main DetailSearch div start 
function mainAdvSch(flag){
	if(flag == "01"){
		mainSchDiv.style.display="block";
	}
}
// main DetailSearch div end 




/* ----------------------------------------- [���̺��d ��ũ����] ---------------------------------------------- */

//oTitleTable ���̺?��ŭ ���Ѵ�.
var  preTableWidth = new Array(document.getElementsByName("oTitleTable").length);

//����� v���Ѵ�.
function setTitleSize(){ 
	var oTitle = document.getElementsByName("oTitleTable");
	var oList = document.getElementsByName("oListTable");

	
	for(var n=0; n<oTitle.length; n++){
		if(oList[n].clientWidth==preTableWidth[n]) continue;
		preTableWidth[n] = oList[n].clientWidth;
	
		oTitle[n].style.width = oList[n].clientWidth;
		var oListRows1 = document.getElementsByName("trTitle")[n];
		var oTitleRows1 = oTitle[n].rows.item(0);
	
		for(var i=0; i<oTitleRows1.length; i++){
			var cWidth = oListRows1.item(i).clientWidth;
			oTitleRows1.item(i).style.width = cWidth;
			oListRows1.item(i).innerText='';
		}
	}
}