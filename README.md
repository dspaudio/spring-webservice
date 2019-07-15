# 0. 전준비
 개발기 os에 node.js 설치. 환경변수 path로 node.js 등록.
eclipse 등의 개발용 ide에 필요한 plugin 설치

# 1. Vue 프로젝트 설치
 터미널에서  vue용 package.json파일이 있는 위치로 이동하여 다음 명령을 입력
```
npm install
```
package.json 내용대로 node module들 설치

# 2. Vue 프론트 개발시
 터미널에서  vue용 package.json파일이 있는 위치로 이동하여 다음 명령을 입력
```
npm run dev
```
개발환경으로 크롬 확장 프로그램인 Vue 개발툴이 활성화되고, Vue 관련 파일이 갱신되었을 때 자동 컴파일해줌

# 3. Vue 프론트 배포할 때
 터미널에서  vue용 package.json파일이 있는 위치로 이동하여 다음 명령을 입력
```
npm run build
```
Spring boot의 기본 port를 application-prod.yml, application-dev.yml로 구분해서 개발환경, 배포환경에 따라 구분해서 포트를 지정해야 함

