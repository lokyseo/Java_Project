# 개발 메모장

## 개선사항

### 현재 문제점
1. ~~설문지 종류에 따라 GUI를 전부 만들어야 한다.~~-> 1번 2번 3번 이런식으로 통일
    - OX를 사용하는 설문지
    - 1번, 2번, 3번과 같은 선택지를 제공하는 설문지
    - 서술형 설문지 등등...
    - (하나로 통합하면 더 빠르게 만들 수 있을 듯)

2. ~~설문지의 통계를 내는 방식이 다르다.~~ 통일시킴
    - MBTI의 경우 1번, 2번, 3번처럼 선택지를 주고, 1번이 많으면 'I'와 같은 방식으로 처리해야 한다.
    - 다른 선호도 조사와 같은 경우:
        - 예를 들어, 1번에 5, 2번에 3과 같은 식으로 응답을 기록하고, 1번에 5번을 선택한 사람 수와 같은 방식으로 통계를 내야 한다.

## 아이디어
- 엑셀 파일 대신 CSV 파일을 사용하는 것은 어떨까?
- 도커를 사용할 계획이신가요?

## 계획

### 파일 구조
- **gui**
    - **Main.java** 
    - **survey** 
    - **gui**
      - PsychologyTestUI (테스트 공통 ui)

    
---

