# EyeQTest

EyeQTest is an Android (Kotlin) mobile app designed for **basic vision screening-style tests** and **daily eye-care routines**. It combines common eye screening interactions (acuity, color vision, astigmatism, contrast, etc.) with a simple **symptoms checker** and a guided **morning eye warm-up** routine.

**Research paper:** https://www.propulsiontechjournal.com/index.php/journal/article/view/3141

---

## Medical disclaimer

EyeQTest is intended for **educational and screening-style use only** and **does not replace a professional eye examination**.

If you experience **eye pain**, **sudden vision changes**, **injury**, **flashes/floaters**, or **persistent symptoms**, seek professional eye care immediately.

---

## What this project includes

### 1) Vision screening-style tests
These modules provide interactive, quick checks commonly used for awareness and basic screening:
- **Color Vision** (Ishihara-style plates)
- **Visual Acuity** (Snellen-style flow)
- **Astigmatism** (line/pattern-based interaction)
- **Macular Degeneration** (Amsler grid)
- **Contrast Sensitivity**
- **Depth Perception**

> Test results should be treated as **informational**, not diagnostic.

### 2) Symptoms checker
A simple flow where users select symptoms and the app suggests **possible eye-related conditions to consider**, encouraging awareness and professional consultation when appropriate.

### 3) Morning eye warm-up
A guided routine aimed at reducing daily strain and encouraging healthier eye habits, including:
- Focus shifting (near/far)
- Eye yoga-style movements
- Blink routine (camera-based detection)
- Hand-eye coordination / reaction-time activity

---

## Tech overview
- **Language:** Kotlin  
- **Platform:** Android  
- **Build:** Gradle  
- **Backend:** Firebase (Authentication + Realtime Database)  
- **Camera / Vision:** CameraX + Google Play Services Vision (blink/face detection)  
- **UI pattern:** Activities + Fragments  

---

## Project structure (high level)

EyeQTest/
├─ app/
│ ├─ src/main/
│ │ ├─ java/ # Kotlin source code
│ │ ├─ res/ # layouts, drawables, strings
│ │ └─ AndroidManifest.xml
│ ├─ build.gradle # app module config
├─ build.gradle # project config
├─ settings.gradle
└─ gradle/ # Gradle wrapper / configs

---

## Privacy and data notes
If enabled/configured, the app may store user-related data such as authentication details and test-related records in Firebase.
- Treat stored results as **sensitive**
- Avoid logging sensitive data
- Use secure Firebase rules for any real deployment

---

## Citation
If you reference EyeQTest in academic or technical writing, cite the published paper:  
https://www.propulsiontechjournal.com/index.php/journal/article/view/3141

---

## Contributing
Contributions are welcome, especially for:
- UI/UX improvements for test flows
- Accessibility improvements (font scaling, contrast, TalkBack support)
- Clearer explanations of results and safety guidance
- Test logic refinements and calibration improvements
