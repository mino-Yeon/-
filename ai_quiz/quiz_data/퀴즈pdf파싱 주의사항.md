# 📋 퀴즈 PDF 파싱 주의사항

> **작성일**: 2025년 10월 10일  
> **프로젝트**: SSAFY AI II 온라인 퀴즈 플랫폼  
> **목적**: PDF → JSON 변환 시 일관성 있는 파싱을 위한 가이드라인

---

## 🎯 **1. 콘텐츠 충실성 (Content Fidelity)**

### ✅ **DO (해야 할 것)**
- **원본 PDF 내용 완전 보존**: 해설의 문구, 수식, 기호를 원본 그대로 유지
- **수학 표기법 정확성**: θ, Σ, Xᵀ, κ², O(n³) 등 수학 기호 정확히 보존
- **한글 텍스트 보존**: 원본 PDF의 한국어 설명을 수정 없이 그대로 활용
- **번호 체계 유지**: ⓵⓶⓷⓸ 등 원본 PDF의 번호 매김 방식 보존
- **복잡도 표기 보존**: O(n²m), O(mn²+n³) 등 시간복잡도 표기 정확히 유지

### ❌ **DON'T (하지 말 것)**
- **해설 편집**: 원본 해설을 임의로 수정하거나 요약하지 않음
- **구조 변경**: PDF 원본의 설명 구조나 순서 변경 금지
- **표기법 통일**: 원본에서 다른 표기를 사용했다면 그대로 보존
- **내용 추가**: 원본에 없는 설명이나 예시 추가 금지

---

## 🏗️ **2. JSON 데이터 구조**

### 📐 **표준 스키마**
```json
{
    "id": 숫자,
    "title": "챕터 X-Y: 제목",
    "icon": "이모지",
    "questions": [
        {
            "difficulty": "상/중/하",
            "time": "X분",
            "question": "문제 내용",
            "options": ["선택지1", "선택지2", "선택지3", "선택지4"],
            "correct": 정답_인덱스(0부터),
            "explanation": "원본 해설 전체"
        }
    ]
}
```

### 🔍 **필수 검증 항목**
- **ID 연속성**: 챕터별로 고유한 ID 부여
- **선택지 개수**: 정확히 4개 선택지 확인
- **정답 인덱스**: 0~3 범위 내 정수값
- **필드 완성도**: 모든 필수 필드 누락 없이 포함

---

## 💻 **3. 코드 포맷팅 보존**

### ✅ **코드 블록 처리 규칙**
- **들여쓰기 유지**: Python 코드의 원본 indentation 보존
- **줄바꿈 보존**: 코드 블록의 원본 구조 유지  
- **공백 문자 보존**: 코드 내 공백과 탭 문자 정확히 보존
- **특수문자 보존**: `\n` 등 이스케이프 문자 정확히 처리

### 🎨 **렌더링 지원**
- **CSS 스타일링**: `white-space: pre-wrap`으로 코드 포맷팅 보존
- **폰트 설정**: monospace 폰트로 코드 가독성 확보

---

## 🎨 **4. 플랫폼 렌더링 분리**

### 📊 **관심사 분리 (Separation of Concerns)**
- **순수 데이터**: JSON은 데이터만 포함, 렌더링 로직 제외
- **HTML 생성**: JavaScript에서 동적으로 HTML 구조 생성
- **선택지 번호**: optionNumbers 배열 `['①', '②', '③', '④', '⑤']`로 자동 매핑
- **스타일링**: CSS로 시각적 표현 담당

### 🚫 **JSON에 포함하지 말 것**
- **HTML 태그**: JSON 데이터에 HTML 마크업 포함 금지
- **CSS 스타일**: 인라인 스타일이나 클래스명 포함 금지
- **JavaScript 코드**: 실행 로직 포함 금지

---

## 📝 **5. 파싱 과정 체크포인트**

### 🔄 **단계별 검증 프로세스**
1. **PDF 섹션 추출**: `\[챕터 X-Y\]` 패턴으로 정확한 구간 식별
2. **문제별 분리**: 각 문제를 개별적으로 수동 파싱
3. **선택지 정리**: 4개 선택지를 배열로 정확히 구성
4. **정답 인덱스**: 0부터 시작하는 인덱스로 정답 매핑
5. **해설 완전성**: 원본 PDF 해설을 누락 없이 포함

### 📋 **파싱 후 검증**
```python
# 각 문제 해설 길이 확인
for i, q in enumerate(chapter_data['questions'], 1):
    print(f'문제 {i}: {q["question"][:50]}...')
    print(f'   해설 길이: {len(q["explanation"])}자')
```

---

## 🔍 **6. 품질 검증 기준**

### ✅ **자동 검증 항목**
- **해설 길이 확인**: 각 문제별 해설 문자 수 출력으로 완전성 검증
- **JSON 유효성**: 올바른 JSON 구조와 UTF-8 인코딩 확인
- **선택지 매핑**: 정답 번호와 선택지 배열 인덱스 정확성 검증
- **인코딩 안정성**: UTF-8 인코딩으로 한글/수학 기호 정상 저장

### 🔬 **수동 검증 항목**
- **수식 정확성**: 수학 공식과 기호가 원본과 일치하는지 확인
- **맥락 보존**: 해설의 논리적 흐름과 설명 순서 확인
- **용어 일관성**: 전문 용어와 개념 설명의 정확성 확인

---

## 📊 **7. 진행 상황 추적**

### ✅ **완료된 챕터**
- **Chapter 0-1**: Python 기초 (5문항) - `chapter_0-1.json`
- **Chapter 0-2**: AI Math (5문항) - `chapter_0-2.json`

### 🔄 **대기 중인 챕터**
- Chapter 1-1: 데이터 EDA 및 모델 학습 실습
- Chapter 1-2: MLP 구현 실습  
- Chapter 2-1: 토큰화_임베딩 실습
- Chapter 2-2: 합성 데이터 제작
- Chapter 3-1: CNN 실습
- Chapter 3-2: 이미지 생성 실습
- Chapter 4-1: RAG 실습
- Chapter 4-2: Agent 서비스 실습
- Chapter 5-1: PEFT 실습
- Chapter 5-2: 경량화 모델 임베디드 실습

---

## 🛠️ **8. 파싱 스크립트 템플릿**

### 🐍 **기본 추출 코드**
```python
import pdfplumber
import re
import json

def extract_chapter_XX():
    with pdfplumber.open('SSAFY AI II 온라인 퀴즈 문항 (총 70개).PDF') as pdf:
        all_text = ''
        for page in pdf.pages:
            text = page.extract_text()
            if text:
                all_text += text + '\n'
        
        # 챕터 X-Y 섹션 찾기
        chapter_start = re.search(r'\[챕터 X-Y\]', all_text)
        if chapter_start:
            start_pos = chapter_start.start()
            next_chapter = re.search(r'\[챕터 다음\]', all_text[start_pos:])
            if next_chapter:
                end_pos = start_pos + next_chapter.start()
                chapter_text = all_text[start_pos:end_pos]
            else:
                chapter_text = all_text[start_pos:]
        else:
            print('챕터 X-Y를 찾을 수 없습니다.')
            return

        # 각 문제별로 수동 파싱 (원본 해설 보존)
        questions = []
        
        # 문제 정의...
        
        # JSON 구조 생성
        chapter_data = {
            'id': 번호,
            'title': '챕터 X-Y: 제목',
            'icon': '🎯',
            'questions': questions
        }

        # JSON 파일로 저장
        with open('chapter_X-Y.json', 'w', encoding='utf-8') as f:
            json.dump(chapter_data, f, ensure_ascii=False, indent=4)
```

---

## ⚠️ **9. 주의사항 요약**

### 🚨 **치명적 실수 방지**
1. **원본 변경 금지**: PDF 해설을 절대 편집하지 말 것
2. **인덱스 실수**: 정답 번호를 0-based index로 정확히 변환
3. **인코딩 오류**: UTF-8으로 저장하여 한글/수학기호 보존
4. **구조 파괴**: JSON 스키마를 임의로 변경하지 말 것
5. **선택지 누락**: 정확히 4개 선택지 확인

### 💡 **효율성 팁**
- **일괄 처리**: 비슷한 패턴의 문제는 템플릿화하여 처리
- **검증 자동화**: 파싱 후 자동 검증 스크립트 실행
- **백업 생성**: 파싱 완료 후 즉시 백업 파일 생성
- **점진적 검토**: 챕터별로 완성 후 개별 검토 진행

---

**📝 마지막 업데이트**: 2025년 10월 10일  
**📁 관련 파일**: `quiz_modular.html`, `chapter_*.json`  
**🔗 참조**: [Template] 지식 정리 노트.pdf, 전체 실습 커리큘럼 요약 문서.xlsx