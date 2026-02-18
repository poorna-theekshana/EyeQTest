# EyeQTest

**Related research paper (published):** https://www.propulsiontechjournal.com/index.php/journal/article/view/3141

EyeQTest is an Android (Kotlin) mobile application for **vision assessment** and **daily eye-care routines**. It combines common screening-style eye tests with a guided “morning eye warm-up” experience and a symptoms checker to help users better understand and manage their eye health. :contentReference[oaicite:0]{index=0}

> Note: EyeQTest is **not a replacement for a professional eye exam**. If you have symptoms, sudden vision changes, pain, or concerns, consult a qualified eye-care professional.

---

## Key features

EyeQTest includes the following modules (as described in the published paper):

### Vision tests
- **Color Vision Test** (Ishihara-style plates) :contentReference[oaicite:1]{index=1}  
- **Astigmatism Test** (visual pattern interaction) :contentReference[oaicite:2]{index=2}  
- **Visual Acuity Test** (Snellen-style chart flow) :contentReference[oaicite:3]{index=3}  
- **Macular Degeneration Test** (Amsler grid interaction) :contentReference[oaicite:4]{index=4}  
- **Contrast Sensitivity Test** (contrast stimuli) :contentReference[oaicite:5]{index=5}  
- **Depth Perception Test** (stereoscopic / depth judgment flow) :contentReference[oaicite:6]{index=6}  

### Symptoms checker
- Users choose symptoms and the app suggests possible eye-related conditions to consider. :contentReference[oaicite:7]{index=7}  

### Morning eye warm-up
A guided routine intended to refresh the eyes and reduce strain, including:
- Focus shift exercises  
- Eye yoga  
- Blinking routines (camera-based)  
- Hand-eye coordination exercise (reaction time) :contentReference[oaicite:8]{index=8}  

---

## Tech stack

- **Language:** Kotlin  
- **Build:** Gradle (Android)
- **Firebase:** Realtime Database + Firebase Auth
- **Camera / Vision:** AndroidX CameraX + Google Play Services Vision (used for blinking / eye detection workflows)
- **Android versions:** `minSdk 24` (Android 7.0+) to `targetSdk 33`

---

## Project structure (high level)

Typical Android Studio structure:

- `app/`  
  - `src/main/java/...` Kotlin source
  - `src/main/res/...` layouts, drawables, strings
- Root Gradle files:
  - `build.gradle`, `settings.gradle`

---

## Getting started

### Prerequisites
- Android Studio (recommended latest stable)
- JDK 8+ (Android Studio bundled JDK is fine)
- A Firebase project (Realtime Database + Auth enabled)

### 1) Clone the repo
```bash
git clone https://github.com/poorna-theekshana/EyeQTest.git
cd EyeQTest
