# 1. Vue 프론트 개발시
터미널에서 다음 명령을 입력
```
cd frontend
npm run dev
```
개발환경으로 크롬 확장 프로그램인 Vue 개발툴이 활성화되고, Vue 관련 파일이 갱신되었을 때 자동 컴파일해줌

# 2. Vue 프론트 배포할 때
```
npm run build
```
Spring boot의 기본 port를 application-prod.yml, application-dev.yml로 구분해서 개발환경, 배포환경에 따라 구분해서 포트를 지정해야 함

