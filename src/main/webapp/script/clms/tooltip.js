// 툴팀 & 모달 
// 작성자 : 신승완 2011.09.01

// 초기화
function init_tooltip() {

	if (!$('.tooltip').length) {
		return;
	}

	// 툴팁을 추가한다.(숨김 상태)
	$('body').append('<div id="tooltip_outer"><div id="tooltip_inner"></div></div>');

	// 빈 변수를 선언한다.
	var $tt_title, $tt_alt;

	var $tt = $('#tooltip_outer');
	var $tt_i = $('#tooltip_inner');

	// 마우스 호버(hover)에 대한 함수를 작성한다.
	$('.tooltip').hover(function() {

		// title 정보를 변수에 저장한 후, title 정보를 제거한다.
		if ($(this).attr('title')) {
			$tt_title = $(this).attr('title');
			$(this).attr('title', '');
		}

		// alt 정보를 변수에 저장한 후, alt 정보를 제거한다.
		if ($(this).attr('alt')) {
			$tt_alt = $(this).attr('alt');
			$(this).attr('alt', '');
		}

		// 텍스트를 추가한다.
		$tt_i.html($tt_title);
		// 툴팁을 보이게 한다.
		$tt.show();
	},
	function() {
		// 툴팁을 숨긴다.
		$tt.hide();
		// 빈 텍스트를 추가한다.
		$tt_i.html('');

		// title 정보를 만든다.
		if ($tt_title) {
			$(this).attr('title', $tt_title);
		}

		// alt 정보를 만든다.
		if ($tt_alt) {
			$(this).attr('alt', $tt_alt);
		}
	// 이동에 대한 함수를 작성한다.
	}).mousemove(function(ev) {
		// 이벤트 좌표
		var $ev_x = ev.pageX;
		var $ev_y = ev.pageY;
		// 툴팁 좌표
		var $tt_x = $tt.outerWidth();
		var $tt_y = $tt.outerHeight();
		// 본문 좌표
		var $bd_x = $('body').outerWidth();
		var $bd_y = $('body').outerHeight();
		// 툴팁을 이동시킨다.
		$tt.css({
			'top': $ev_y + $tt_y > $bd_y ? $ev_y - $tt_y : $ev_y,
			'left': $ev_x + $tt_x + 20 > $bd_x ? $ev_x - $tt_x - 10 : $ev_x + 15
		});
	});
}

//초기화.
function init_modal() {

	if (!$('label._modal').length) {
		// 요소가 존재하지 않는다면, 함수를 빠져나간다.
		return;
	}

	// IE 6 탐지 (boolean)
	var $IE6 = typeof document.addEventListener !== 'function' && !window.XMLHttpRequest;

	// 몇몇 계산을 수행한다.
	function sizeModal() {

		// 모달의 크기를 계산한다.
		var $modal = $('#modal_window');
		var $modal_width = $modal.outerWidth();
		var $modal_height = $modal.outerHeight();
		var $modal_top = '-' + Math.floor($modal_height / 2) + 'px';
		var $modal_left = '-' + Math.floor($modal_width / 2) + 'px';

		// 모달을 설정한다.
		$('#modal_window').css('margin-top', $modal_top).css('margin-left', $modal_left);
	}

	/* IE 6용 */ 
	function positionModal() {

		// 적절한 위치에 모달을 위치시킨다.
		$('#modal_wrapper').css('top', $(document).scrollTop() + 'px');
	}

	// 모달을 표시하는 함수이다.
	function showModal() {
		if ($IE6) {
			positionModal();
		}

		// 모달의 래퍼를 나타낸다.
		$('#modal_wrapper').show();

		// 크기를 계산한다.
		sizeModal();

		// 모달 창을 나타낸다.
		$('#modal_window').css('visibility', 'visible').show();
	}

	// <body>의 끝부분에 모달을 추가한다.
	$('body').append('<div id="modal_wrapper"><div id="modal_overlay"></div><div id="modal_window"><div id="modal_bar"><strong>Modal window</strong><a href="#" id="modal_close">Close</a></div><div id="modal_content"></div></div>');

	// modal이라는 클래스를 가지는 링크를 찾는다. 
	$('label._modal').click(function() {
		// href="..."를 검사한다.
		var $titleText = $(this).text();

		$('#modal_content').html("<strong>"+$titleText+"</strong>" + " 에 대한 자료가 없습니다.");
		showModal();

		// 모달의 제목을 결정한다.
		if ($(this).attr('title')) {

			// 제목을 추가한다.
			$('#modal_bar strong').html($(this).attr('title'));

		} else if ($(this).html() !== '') {

			// 링크의 텍스트를 추가한다.
			$('#modal_bar strong').html($(this).html());
		}

		// 링크로 이동하지 않는다.
		this.blur();		
		return false;
	});

	// 모달 요소를 숨긴다.
	$('#modal_close').click(function() {

		// 모달을 숨긴다.
		$('#modal_wrapper').hide();

		// 모달의 콘텐트를 제거한다.
		//$('#modal_content').html('');	// a 요소 시 사용
		$('#modal_wrapper').remove();	// a 요소가 아닌 경우 설정

		// 모달의 제목을 재설정한다.
		$('#modal_bar strong').html('Modal window');

		// 링크로 이동하지 않는다.
		this.blur();
		return false;
	});

	// IE 6인 경우, 브라우저의 스크롤 이벤트를 연결한다.
	if ($IE6) {
		$(window).scroll(function() {
			if ($('#modal_wrapper').is(':visible')) {
				positionModal();
			}
		});
	}
}
