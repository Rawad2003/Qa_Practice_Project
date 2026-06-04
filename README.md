# QAPractice – Selenium UI Automation Framework

UI test automation for the [QA Practice](https://www.qa-practice.com/) demo site, built with **Java + Selenium + TestNG** using the **Page Object Model**.

> Public practice site only — no real data is submitted or persisted. All tests run against the public demo features.

---

## Table of Contents

- [Tech Stack](#tech-stack)
- [What's Covered](#whats-covered)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Running the Tests](#running-the-tests)
- [Configuration](#configuration)
- [Known Issues](#known-issues)
- [Documentation](#documentation)

---

## Tech Stack

| Area | Tool |
|------|------|
| Language | Java 17+ |
| Browser automation | Selenium WebDriver 4.38 |
| Test framework | TestNG 7.9 |
| Build | Maven |
| Driver management | WebDriverManager |
| Design pattern | Page Object Model + OOP |

---

## What's Covered

The suite automates the thirteen interactive feature areas of qa-practice.com, spanning form inputs, UI controls, frames, pop-ups, and a full practice form. Each test navigates fresh, drives the page through a Page Object, and evaluates the outcome with a shared `assertResult(expected, urlBefore)` helper — checking for a success result, URL change, or absence of a 404 on valid cases, and for error feedback or staying on the page for invalid cases.

| # | Section | What's validated |
|---|---------|------------------|
| 1 | Text input field | Valid/invalid string rules, length boundaries |
| 2 | Email field | Format validation |
| 3 | Password field | Complexity rules |
| 4 | Buttons | Simple, looks-like-a-button, disabled |
| 5 | Checkboxes | Single, multi |
| 6 | Select dropdowns | Single, multi, all combinations |
| 7 | New tab | Link, button |
| 8 | Textareas | Single, multiple |
| 9 | Alerts | Alert, confirm, prompt |
| 10 | Drag & Drop | Boxes and images tabs (`Drop here` → `Dropped!`) |
| 11 | Iframe | Presence/attrs, context switching, navbar, header CTAs, album cards, back-to-top, footers |
| 12 | Pop-up | Modal tab and iframe-popup tab with copy/paste validation |
| 13 | Practice form | Required/optional fields, dependent State/City dropdowns, results modal |

Test design uses Equivalence Partitioning, Boundary Value Analysis, and Error Guessing, covering positive, negative, and boundary cases — including requirement-driven negative tests for placeholder `href="#"` links. Data-driven cases run via TestNG `@DataProvider`, so each email, password, checkbox, select and practice-form data row is its own test. See the [Test Plan](docs/test-plan/) for the full strategy.

---

## Project Structure

```
qapractice-selenium/
├── src/
│   ├── main/java/com/qapractice/automation/
│   │   ├── common/
│   │   │   └── base/        # BaseSetupManager – driver setup, runtime browser selection
│   │   └── pages/           # Page Objects (one per feature section)
│   └── test/
│       └── java/.../tests/  # QaPractice – ordered via @Test(priority)
├── docs/                    # Test Plan, Test Cases, Bug Report, RTM
├── testng.xml               # Suite definition
└── pom.xml
```

---

## Getting Started

### Prerequisites

- **JDK 17+** (or adjust `maven.compiler.release` in `pom.xml` to your JDK)
- **Maven 3.9+**
- **Chrome, Edge and/or Firefox** (WebDriverManager downloads the matching driver automatically)


At startup you are prompted to select a browser via a `Scanner` in `BaseSetupManager`:

```
Select browser: 1 = Chrome, 2 = Edge, 3 = Firefox
```

> **Note for CI / unattended runs:** the interactive prompt blocks headless pipelines. Parameterize or default the browser selection before running in CI (see *Risks & mitigations* in the Test Plan).

---

## Configuration

Tests are ordered with `@Test(priority = ...)` and navigate fresh to each feature. A `Thread.sleep(500)` between actions stabilizes interactions with the live public site. Browser choice is made at runtime through `BaseSetupManager`:

| Setting | Description | Default |
|---------|-------------|---------|
| `base.url` | Application under test | `https://www.qa-practice.com/` |
| Browser selection | `1 = Chrome`, `2 = Edge`, `3 = Firefox` (runtime Scanner prompt) | Chrome |

---

## Known Issues

| ID | Summary | Module | Severity | Status |
|----|---------|--------|----------|--------|
| BUG-001 | Album placeholder links (Twitter / Facebook / Email / Album) and card View/Edit are `href="#"` and do not navigate | Iframe / Album | Major | Open |
| BUG-002 | Historical PracticeForm wiring bug (`selectHobby` / `selectCity`) | Practice form | Major | Closed (fixed) |
| BUG-003 | Mobile-number length validation — confirm whether non-10-digit values are rejected | Practice form | Minor | Open (watch) |
| BUG-004 | Iframe navbar links non-functional | Iframe / Album | Minor | Open |

See [`docs/`](docs/) for full reproduction steps.

---

## Documentation

All QA deliverables are in [`docs/`](docs/):

| Document | Location |
|----------|----------|
| Test Plan | [`docs/test-plan/`](docs/test-plan/) |
| Test Cases | [`docs/test-cases/`](docs/test-cases/) |
| Bug Report | [`docs/bug-report/`](docs/bug-report/) |
| Requirements Traceability Matrix (RTM) | [`docs/rtm/`](docs/rtm/) |

`TC_ID`, `Bug_ID` and `Requirement_ID` cross-reference consistently across all deliverables.
