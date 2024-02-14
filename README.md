# 콕콕 쿡쿡 :  레시피 추천 및 공유 사이트

## 개발 배경 및 개발 목적
구글 검색어 트렌드를 조회한 결과 코로나로 인한 상황적 요인으로 집에서 요리하는 빈도수가 높아지면서 레시피에 대한 관심도가 지속적으로 증가한 것을 확인할 수 있었으며 교보문고의 제공한 정보에 따르면 레시피를 담은 요리책이 올해 1월부터 5월까지 요리책 판매량이 지난해 같은 기간에 비해 18.9% 증가했다는 것을 알 수 있었다. 이를 바탕으로 레시피에 대한 관심이 지속적으로 증가하는 상황 속에서 여러 사람과 다양한 레시피를 공유할 수 있게 하는 것이 콕콕 쿡쿡의 개발 목적이다.

## 차별성
+ 전국 팔도의 특산물을 재료로 추가하여 지역 특산물의 소비 촉진을 장려함
+ 사용자들이 가지고 있는 재료를 선택하여 이에 맞는 레시피를 제공하기 때문에 재료구비에 대한 부담을 덜어줌
+ 사용자들에 대한 설문조사를 진행하여 사용자들의 식생활 및 사이트 만족도에 대한 정보를 얻을 수 있음

## 사용기술 및 개발 환경

<img src="https://img.shields.io/badge/html-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white"> <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white"> <img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"> <img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white">

## 구현 기능 

+ 사용자 회원가입 / 로그인/ 회원 탈퇴 / 내 정보 수정 / 아이디 찾기 / 비밀번호 찾기
+ 네이버 API 를 이용하여 별도의 회원가입 없이 간편하게 로그인 할 수 있는 기능 제공
+ Mail API 를 이용하여 회원가입 및 비밀번호 찾기, 이벤트 정보 제공 등에서 사용자에게 메일 발송 기능
+ 사용자 레시피 등록/ 상세보기 / 수정
+ 이벤트 게시판을 만들어 사용자에게 이벤트에 대한 정보 제공
+ 설문조사 페이지를 만들어 사용자가 설문조사를 하고 이에 대한 통계를 추출
+ 관리자 페이지를 구현하여 사용자 관리, Q&A 관리, 이벤트 관리, 공지사항 관리

## 핵심 내용
<img src ="https://user-images.githubusercontent.com/73329610/127971641-2dd3fb6b-cca2-45a2-a027-9458d160770b.png" width="50%" height="50%">

* 메인 페이지 
  * 인기 레시피 : 조회 수가 높은 순으로 네 개의 레시피를 보여줌 
  * 특산물을 이용한 레시피 : 지역 특산물을 사용한 레시피의 최신순으로 네 개의 레시피를 보여줌
  * 사용자들이 집에 있는 레시피 : 원하는 재료를 선택 후 ' 이 재료로 추천받기' 를 클릭하면 해당 재료에 맞는 레시피 목록을 보여줌 

<img src ="https://user-images.githubusercontent.com/73329610/127974742-1663bc4d-71cd-425e-8763-941a8cf35dda.png" width="50%" height="50%">

* 레시피 작성 페이지
  * 사용자가 해당 페이지에서 레시피를 추가할 수 있는 기능
  * 모든 값을 정확하게 입력해야 레시피 등록 가능
  * 레시피를 추가하면 메인 페이지에 레시피가 노출
  * 자신이 작성한 레시피는 마이 페이지에서 확인 가능

<img src ="https://user-images.githubusercontent.com/73329610/127976877-28bc7642-c581-4fc9-8821-58858b6d2363.png" width="50%" height="50%"><img src ="https://user-images.githubusercontent.com/73329610/127976609-e0288243-c94a-455a-9c74-b7dc77d02d83.png" width="50%" height="50%">

* 관리자 통계관리 - 설문조사 통계 페이지
  * 사용자가 설문조사를 작성하면 DB에 저장되고, 설문조사를 구글 차트 API를 이용하여 집계처리
  * 설문조사를 통해 사용자들의 식습관과 사이트에 대한 만족도를 파악하여 사이트를 및 콘텐츠를 개선 가능

## 설계 주안점
+ 인기 레시피 및 지역 특산물을 이용한 레시피 정보 제공 
+ 집에 있는 재료를 선택하여 사용자가 선택한 재료로 레시피 정보 제공
+ 주제별 레시피, 재료별 레시피, 특산물별 레시피 등 다양한 카테고리를 구현
+ 마이페이지에 신이 스크랩한(다른 사용자의 레시피를 찜하는 기능) 레시피 목록을 보여주는 기능 구현 
+ 사이트 내에서 다양한 콘텐츠를 제공하기 이벤트 게시판 구현

## 팀원별 단위 업무

서 민 기        : DB스키마 생성, DB관리, 레시피 (등록, 상세보기, 수정)    
윤 종 훈        : 스토리보드 작성 , ERD 설계 , 페이지 디자인, 공지사항 게시판 , Q&A 게시판 , 아이디 찾기 , 설문조사 구현    
이 정 호        : 웹 사이트 디자인 , 스토리보드 제작, 사이트 배너 및 이벤트 이미지 제작 , 이벤트 게시판 , 관리자(재료 관리, 특산물 관리, 통계관리)    
류 승 재        : Q&A 답변 기능 , 비밀번호 찾기 , 공지 사항 게시판 , 설문조사 기능 구현    
오 진 영        : 관리자(사용자 관리, Q&A관리, 이벤트 관리 , 공지사항 관리)   
         


