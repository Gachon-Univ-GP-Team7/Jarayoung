# 자라영(Jarayoung)   
#### [Tech Spec & How to use](https://pear-harp-86c.notion.site/Jarayoung-Wiki-2d38d0302ddb40fb951fe28afe9a7049)
---
영유아 발달 단계 측정을 위한 앱

미취학 아동을 대상으로 국가에서는 필수로 영유아검진을 시행합니다.
아이들의 신체검사, 발달 단계 평가를 통해 조기에 아이들의 문제를 진단합니다.
하지만 영유아 검사는 병원 측의 부담과 맞벌이 가정 증가 등의 이유로 
미수검율과 평가가 주관적으로 이루어진다는 문제점이 있습니다.
### 현행 영유아 검진의 문제점
1. 복잡한 문진표 
2. 평가가 부모님의 기억에 의존하여 매우 주관적
3. 아이들의 성장 속도 차이 
4. 영유가 검진은 특정시기에 딱 한번으로 한정되어 있기 때문에 장기적으로 아이의 상태를 관찰에 부적합.


#### -> 이러한 문제를 인식해, <br>      저희는 간단하고, 객관적이고 시공간의 제약이 없는 영유아 검진 자동화 시스템<br>       "자라영"을 기획하게 되었습니다.

- 자라영은 **음성인식**과 **행동인식**을 통해 아이들의 발달 단계를 예측합니다.
- 일상적인 영상과 대화를 기반으로 모니터링 하기 때문에 간단합니다.

## System Architecture

---  
<img width="945" alt="image" src="https://github.com/Gachon-Univ-GP-Team7/Jarayoung/assets/101817814/2be00567-ad14-4aa9-b45c-60a71f79ed03">

- 안드로이드 스튜디오 플랫폼을 이용하였고 언어는 자바를 사용해서 앱을 만들었습니다.
- 안드로이드 버전은 12. 사용 라이브러리는 HTTP 통신을 위한 레트로핏 2, 차트를 그리기 위한 MPAndroidchart를 사용했습니다.
- WS로는 Nginx, WAS는 SpringBoot(Tomcat), DB는 RDS상의 MySQl을 사용했습니다.
- 라이브러리는 데이터 베이스를 연결하기 위해 사용하는 JDBC를 사용하였습니다.

## 음성인식 & 행동인식

---
전체적인 흐름도 입니다.  
<img width="870" alt="image" src="https://github.com/Gachon-Univ-GP-Team7/Jarayoung/assets/101817814/afec1e30-b8ec-4410-b596-c590403690e4">

### 음성 인식
Dataset은 AI 허브 자유대화 음성(소아남여, 유아 등 혼합)을 사용했습니다.  
제이슨 형식의 메타 데이터가 들어가 있으며,
연령별 데이터의 비율이 달라서 비율을 자체적으로 조정해 사용했습니다.    
<img width="261" alt="image" src="https://github.com/Gachon-Univ-GP-Team7/.github/assets/101817814/653cb77b-039b-403c-aeb0-e981396a3885">  
음성인식은 **딥러닝**과 **다중회귀** 방법을 사용해서 아이들의 발화 나이를 예측했습니다.     
전처리 과정은 동일하게 토큰화, 불용어 제거, 임베딩, 분류의 과정을 거쳤습니다.    
<img width="809" alt="image" src="https://github.com/Gachon-Univ-GP-Team7/Jarayoung/assets/101817814/c2776c9c-5acf-4997-a85d-2671a71d2d53">  
- **딥러닝**
  - 케라스를 사용해 다중클래스 분류 모델을 만들었습니다.
  - 3개의의 LSTM 레이어와 하나의 소프트맥스 레이어를 사용했습니다.
  - 31퍼센트의 정확도를 보이고 있습니다.
- **다중 회귀** 
  - 아이들의 연령별로 형태소의 평균 갯수, 최대 갯수, 최소 갯수를 파악하여 문장을 분석했습니다.
  - 이를 바탕으로 한 문장에서 형태소의 수, 단어의 수를 기준으로 다중회귀분류를 했습니다.
  - 약 21-26%의 정확도를 보입니다.

### 행동 인식
- Openpose API를 영유아 관절 인식에 사용했습니다.
- 인식된 관절을 바탕으로 유아 행동 인식에는 슬로우패스트 네트워크를 사용하고 있습니다.</br></br></br></br>



## App View
<img width="987" alt="image" src="https://github.com/Gachon-Univ-GP-Team7/.github/assets/101817814/5949f9d1-0281-4bcf-a136-933e7ade0312">  

<img width="1195" alt="image" src="https://github.com/Gachon-Univ-GP-Team7/.github/assets/101817814/85331e78-6699-4258-9809-eddcfaf92715">

