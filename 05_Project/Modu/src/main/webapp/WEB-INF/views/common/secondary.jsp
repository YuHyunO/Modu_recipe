<%@ page language="java" contentType="text/html; charset=utf-8"%>
<script src="/js/cookie.js"></script>
<!--사이드 영역(secondary) 시작-->
<div id="secondary" class="col-md-3">
	<div class="widget post-type-widget pt-3">
		<!-- widget 최근 본 게시물 -->
		<div class="widget-title-outer">
			<h3 class="widget-title">최근 본 레시피</h3>
		</div>
		<!-- 최근 본 레시피 영역 -->
		<ul id="recent-recipe-area"></ul>
	</div><!-- end class="widget post-type-widget pt-3 pb-3" -->
		
	<!--새로운 태그들-->
	<div class="widget post-type-widget pt-3">
		<div class="widget-title-outer">
			<h3 class="widget-title">추천 레시피 키워드</h3>
		</div>
		<div class="tagcloud d-flex">
			<div class="row px-2">
				<a href="#" class="me-1 mb-1">#나시고랭</a>
				<a href="#" class="me-1 mb-1">#칵테일새우</a>
				<a href="#" class="me-1 mb-1">#스테이크</a>
				<a href="#" class="me-1 mb-1">#돈까스</a>
				<a href="#" class="me-1 mb-1">#새우볶음밥</a>
				<a href="#" class="me-1 mb-1">#아보카도</a>
				<a href="#" class="me-1 mb-1">#감자</a>
				<a href="#" class="me-1 mb-1">#낙지</a>
				<a href="#" class="me-1 mb-1">#두부요리</a>
				<a href="#" class="me-1 mb-1">#매운탕</a>
			</div>
		</div>
	</div>	<!-- end widget -->

	<!--비스포크 광고 배너 -->
	<div class="widget post-type-widget pt-3" style="text-align:center;">
		<a href="#">
		<img id="bespoke_ad" class="rounded-3 banner"
			src="/imgs/mypage/ad_bespoke.PNG"
			alt="Banner" />
		</a>
	</div>
	<!--락앤락 광고 배너-->
	<div class="widget post-type-widget pt-3" style="text-align:center;">
		<a href="#"> <img id="lockandlock_ad" class="rounded-3 banner"
			src="/imgs/index/lock_ad.gif" alt="lockandlock_ad.gif" />
		</a>
	</div>	<!-- end 광고 배너 -->

</div> <!-- end #secondary, 사이드영역 끝 -->