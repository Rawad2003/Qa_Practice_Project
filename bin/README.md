# QAPractice – Selenium UI Automation Framework

A Selenium + TestNG + Maven UI-automation suite (Page Object Model) for the public practice site [qa-practice.com](https://www.qa-practice.com/)

> Public practice site only — no real data is persisted. All tests run against the public demo features.

---

## Table of Contents

- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [How to Run](#how-to-run)
- [Test Coverage](#test-coverage)
- [Known Issues / By-Design Failures](#known-issues--by-design-failures)
- [Design Choices](#design-choices)
- [Results & Reports](#results--reports)
- [Documentation](#documentation)

---

## Tech Stack

| Area | Tool |
|------|------|
| Language | Java 21+ |
| Browser automation | Selenium WebDriver 4.38 |
| Test framework | TestNG 7.9 |
| Build | Maven |
| Driver management | WebDriverManager |
| Design pattern | Page Object Model + one test class per feature |

---

## Project Structure

The suite is organized **one test class per feature**. The old monolithic `QaPractice` class was removed; the page objects were left unchanged.

```
src/main/java/com/practice/QAPractice/
├── BasePage/                 # BasePage (page-object base)
├── SingleUIElementsPages/    # 12 page objects:
│     TextInputPage, EmailPage, PasswordPage, ButtonPage, CheckboxPage,
│     SelectPage, NewTabPage, TextAreaPage, AlertPage, DragAndDropPage,
│     IframePage, PopUpPage
├── FormsPage/                # PracticeFormPage
├── SideBar/                  # SidebarPage
└── MiddleBar/                # MiddleBarPage

src/test/java/com/practice/QAPractice/
├── BasePage/                 # BaseSetupManager, BaseTest
├── tests/singleui/           # 20 per-feature test classes:
│     TextInputTest, EmailTest, PasswordTest,
│     SimpleButtonTest, LooksLikeAButtonTest, DisabledButtonTest,
│     SingleCheckboxTest, CheckboxesTest,
│     SingleSelectTest, MultipleSelectTest,
│     NewTabLinkTest, NewTabButtonTest,
│     SingleTextAreaTest, MultipleTextAreaTest,
│     AlertBoxTest, ConfirmationBoxTest, PromptBoxTest,
│     DragAndDropBoxTest, DragAndDropImagesTest, IframeTest , ModalPopUpTest, IframePopUpTest
├── tests/forms/              # PracticeFormTest
├── SideBar/                  # SidebarTest
└── MiddleBar/                # MiddleBarTest
```


## Prerequisites

- **JDK 21+**
- **Maven 3.9+**
- **Chrome, Edge and/or Firefox** — WebDriverManager downloads the matching driver automatically

---

## How to Run

Run from your IDE's **TestNG runner** — execute a single per-feature class (e.g. `TextInputTest`), a single method, or the whole `src/test/java` tree.

When the suite starts, `BaseSetupManager` prompts for the browser via a `Scanner`:

```
Select browser: 1 = Chrome, 2 = Edge, 3 = Firefox
```


## Test Coverage

Sections 1–13 plus the Sidebar and MiddleBar suites. The owning test class is shown for each.

| # | Section | Owning test class(es) |
|---|---------|------------------------|
| 1 | Text input field (data-driven) | `TextInputTest` |
| 2 | Email field (data-driven) | `EmailTest` |
| 3 | Password field (data-driven) | `PasswordTest` |
| 4 | Buttons | `SimpleButtonTest`, `LooksLikeAButtonTest`, `DisabledButtonTest` |
| 5 | Checkboxes | `SingleCheckboxTest`, `CheckboxesTest` |
| 6 | Select dropdowns (incl. all 60 combos) | `SingleSelectTest`, `MultipleSelectTest` |
| 7 | New tab | `NewTabLinkTest`, `NewTabButtonTest` |
| 8 | Textareas | `SingleTextAreaTest`, `MultipleTextAreaTest` |
| 9 | Alerts | `AlertBoxTest`, `ConfirmationBoxTest`, `PromptBoxTest` |
| 10 | Drag & Drop | `DragAndDropBoxTest`, `DragAndDropImagesTest` |
| 11 | Iframe album | `IframeTest` |
| 12 | Pop-ups | `ModalPopUpTest`, `IframePopUpTest` |
| 13 | Practice form | `PracticeFormTest` |
| — | Sidebar / MiddleBar | `SidebarTest`, `MiddleBarTest` |



## Known Issues / By-Design Failures

**BUG-01 (Major, Open) — Iframe album placeholder links/buttons are non-functional.**

Inside the iframe album, `Follow on Twitter`, `Like on Facebook`, `Email me`, `Album`, and every card's `View` and `Edit` are `<a href="#">` placeholders that do not navigate or perform any action. Six requirement-driven negative tests in `IframeTest` assert the *intended* behavior and therefore **fail by design**, documenting the defect rather than indicating a regression:

```
IframeTest.test_9_2_Iframe_TwitterLink
IframeTest.test_9_2_Iframe_FacebookLink
IframeTest.test_9_2_Iframe_EmailLink
IframeTest.test_9_2_Iframe_AlbumLink
IframeTest.test_9_2_Iframe_ViewButtons_AllCards
IframeTest.test_9_2_Iframe_EditButtons_AllCards
```

These are the **6 failures** in the run summary. All other tests pass.

**Fixed during development** (retained for history): `selectHobby` toggled gender instead of Sports/Reading/Music (**BUG-02**); `selectCity` opened the State dropdown instead of City (**BUG-03**); a fixed `#fixedban` ad-banner intercepted Submit/dropdown clicks (**BUG-04**, fixed by hiding the banner). Note: the mobile-number field validation is **correct** (rejects fewer than 10 digits; `maxlength=10` caps longer input) and is *not* a defect.

---

## Design Choices

- **Page Object Model** — locators and page behavior live in page classes; tests stay readable.
- **One test class per feature** — each feature has a focused TestNG class, all extending a shared `BaseTest`.
- **Explicit waits instead of `Thread.sleep`** — every wait is condition-based (`WebDriverWait` + `ExpectedConditions`) via the `BaseTest` helpers; there is no `Thread.sleep` in the tests. This replaced the old monolith's fixed sleeps and made the suite faster and far less flaky.
- **Data-driven testing** — TestNG `@DataProvider` expands text/email/password/select inputs into individual cases.
- **JavaScript click** — used for off-screen or animated elements that intercept native clicks.
- **Scroll-position polling** — animated scroll-to-top is verified by polling `window.pageYOffset == 0`, not by sleeping.
- **`#fixedban` hidden** — the overlapping ad-banner is hidden via JS before interacting with the practice form.
- **Iframe handling** — explicit `switchTo().frame(...)` / `defaultContent()` around album assertions; the iframe-popup "Check" reloads the page *with* the input form, which the flow accounts for.
- **Shared `assertResult(expected, urlBefore)`** — one assertion helper for success-result / URL-change / no-404 (valid) and error-feedback / stayed-on-page (invalid).

---

## Results & Reports

- Running from the IDE TestNG runner produces TestNG's HTML output under `test-output/` (e.g. `test-output/index.html` / `emailable-report.html`).
- The 6 by-design failures listed above are expected; any *other* failure indicates a real regression worth investigating.

---

## Documentation

Full QA deliverables accompany this repository:

| Document | File |
|----------|------|
| Test Plan | `TestPlan_QAPractice.docx` |
| Test Cases | `TestCases_QAPractice.xlsx` |
| Bug Report | `BugReport_QAPractice.xlsx` |
| Requirements Traceability Matrix | `RTM_QAPractice.xlsx` |

`TC_ID`, `Bug_ID` and `Requirement_ID` cross-reference consistently across all deliverables; the Test Cases and RTM reference the owning per-feature test class and method.
