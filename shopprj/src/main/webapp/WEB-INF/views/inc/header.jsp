<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 로그인 회원가입 창 -->
<div id="userModal" class="cd-user-modal"> <!-- this is the entire modal form, including the background -->
<div class="cd-user-modal-container"> <!-- this is the container wrapper -->
<ul class="cd-switcher">
  <li><a href="/home/in">로그인</a></li>
  <li><a href="/home/reg">회원가입</a></li>
</ul>

<!-- 로그인 -->
<form name="loginForm" method="post" action="/home/login" class="cd-form">
	<div id="cd-login">
		<p class="fieldset">
			<label class="image-replace cd-username" for="signin-username">아이디</label>
			<input type="text" id="userId" name="userId" class="full-width has-padding has-border" placeholder="아이디">
		</p>

		<p class="fieldset">
			<label class="image-replace cd-password" for="signin-password">비밀번호</label>
			<input type="password" id="userPwd" name="userPwd" class="full-width has-padding has-border" placeholder="비밀번호">
			<a href="#0" class="hide-password">show</a>
		</p>
		<p class="fieldset">
			<input type="button" value="로그인" class="full-width has-padding"onclick="fnLogin()" >
		</p>

		<p class="cd-form-bottom-message">
			<a href="#0">비밀번호 찾기</a>
		</p>
	</div>
</form>

<!-- 회원가입 -->
<form name="registerForm" method="post" action="/home/register" class="cd-form">
	<div id="cd-signup">

		<p class="fieldset">
			<label class="image-replace cd-email" for="signup-email">유저 이메일</label>
			<input type="email" id="userEmail" name="email" class="full-width has-padding has-border" placeholder="이메일">
		</p>

		<p class="fieldset">
			<label class="image-replace cd-username" for="signup-username">유저 이름</label>
			<input type="text" id="userName" name="name" class="full-width has-padding has-border" placeholder="이름">
		</p>

		<p class="fieldset">
			<label class="image-replace cd-username" for="signup-email">유저 아이디</label>
			<input type="text" id="userId" name="id" class="full-width has-padding has-border" placeholder="아이디">
		</p>

		<p class="fieldset">
			<label class="image-replace cd-password" for="signup-password">유저 비밀번호</label>
			<input type="password" id="userPwd" name="pwd" class="full-width has-padding has-border" placeholder="비밀번호">
			<a href="#0" class="hide-password">Hide</a>
		</p>

		<!-- <p class="fieldset">
	<label class="image-replace cd-password" for="signup-password">비밀번호 확인</label>
	<input type="password" id="confirmPwd" name="confirmPwd" class="full-width has-padding has-border" placeholder="비밀번호 다시 입력">
	<a href="#0" class="hide-password">Hide</a>
</p> -->

<p class="fieldset">
	<input type="button" value="가입하기" class="full-width has-padding" onclick="fnRegister()" >
		</p>
	</div>
</form>

		<!-- <a href="#0" class="cd-close-form">Close</a> -->
<!-- cd-signup -->

<div id="cd-reset-password"> <!-- reset password form -->
  <p class="cd-form-message">비밀번호를 잃었습니까? 이메일 주소를 입력하십시오. 새 비밀번호를 만들 수 있는 링크를 받게 됩니다.</p>

  <form class="cd-form">
    <p class="fieldset">
      <label class="image-replace cd-email" for="reset-email">유저 이메일</label>
      <input class="full-width has-padding has-border" id="reset-email" type="email" placeholder="이메일">
    </p>

    <p class="fieldset">
      <input class="full-width has-padding" type="submit" value="비밀번호 초기화">
    </p>
  </form>

  <p class="cd-form-bottom-message"><a href="#0">로그인으로 돌아가기</a></p>
</div> <!-- cd-reset-password -->
<!-- <a href="#0" class="cd-close-form">Close</a> -->
</div> <!-- cd-user-modal-container -->
</div>

  <div class="header-area">
      <div class="container">
          <div class="row">
              <div class="col-md-8">
                  <div class="user-menu">
                      <ul>
                          <!-- <li><a href="#"><i class="fa fa-user"></i>내정보</a></li> -->
                          <li><a href="#"><i class="fa fa-heart"></i>찜</a></li>
                          <li><a href="cart.html"><i class="fa fa-user"></i>장바구니</a></li>
                      </ul>
                  </div>
                  
              </div>
              
              <c:choose>
              	<c:when test="${not empty userName}">
              		<div class="col-md-4">
	                  <div class="user-menu text-right">
	                      <ul>
	                      	  <li><a href="#"><i class="fa fa-user"></i> ${userName} 님</a></li>
	                          <li><a href="/home/logout" ><i class="fa fa-user"></i>로그아웃</a></li>
	                      </ul>
	                  </div>
	                </div>
              	</c:when>
              	<c:otherwise>
					<div class="col-md-4">
						<div class="user-menu cd-register text-right">
							<ul>
								<li><a href="#"><i class="fa fa-user"></i>로그인</a></li>
							</ul>
						</div>
					</div>
				</c:otherwise>
              </c:choose>
              
          </div>
      </div>
  </div> <!-- End header area -->

<div class="site-branding-area">
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
                <div class="logo">
                    <h1><a href="/home/main">IT<span>collection</span></a></h1>
                </div>
            </div>
            
            <div class="col-sm-6">
                <div class="shopping-item">
                    <a href="cart.html">장바구니 - <span class="cart-amunt">$800</span> <i class="fa fa-shopping-cart"></i> <span class="product-count">5</span></a>
                </div>
            </div>
        </div>
    </div>
</div> <!-- End site branding area -->

<div class="mainmenu-area">
    <div class="container">
        <div class="row">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div> 
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/home/main">홈</a></li>
                    <li><a href="shop.html">상품 페이지</a></li>
                    <li><a href="/home/board">전체 게시판</a></li>
                </ul>
            </div>  
        </div>
    </div>
</div> <!-- End mainmenu area -->