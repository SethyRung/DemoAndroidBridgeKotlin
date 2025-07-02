# 🤖 DemoAndroidBridgeKotlin

A companion Android project that demonstrates integrating a native **Android WebView** with a **Nuxt frontend** using [`WebViewJavascriptBridge`](https://github.com/RDSunhy/WebViewJavascriptBridge). This setup enables two-way communication between your Kotlin Android app and a Nuxt (Vue) web app, ideal for hybrid mobile applications.

---

## 🔗 Related Project

This project works with the Nuxt frontend demo:
👉 **[DemoNuxtBridge](https://github.com/SethyRung/DemoNuxtBridge)**
🌐 **[Live Demo (Nuxt)](https://demo-nuxt-bridge.vercel.app/)**

---

## 🎯 Purpose

* Embed a Nuxt (or any web) app inside Android’s native `WebView`
* Use **WebViewJavascriptBridge** to send and receive messages between native code and the web
* Provide a working hybrid communication model between Vue (web) and Kotlin (native)

---

## 📱 How It Works

This project uses the [`WebViewJavascriptBridge`](https://github.com/RDSunhy/WebViewJavascriptBridge) library to enable native ↔ web communication.

### ✅ Web → Native

```js
window.WebViewJavascriptBridge.send('Hello from Nuxt!', function (response) {
  console.log('Native replied:', response);
});
```

### ✅ Native → Web

```kotlin
bridge.callHandler("jsHandler", "Hi from Android!") {
    // Optional callback from JS
}
```

---

## 🧱 Tech Stack

* Kotlin (Android)
* Native `WebView`
* [WebViewJavascriptBridge for Android](https://github.com/RDSunhy/WebViewJavascriptBridge)
* Targeting API 24+ (adjustable)

---

## 📦 Setup Instructions

1. Clone the repo:

   ```bash
   git clone https://github.com/SethyRung/DemoAndroidBridgeKotlin.git
   ```

2. Open in **Android Studio**.

3. Ensure your emulator or device has internet access.

4. Update the URL to point to your Nuxt frontend if needed:

   ```kotlin
   webView.loadUrl("https://demo-nuxt-bridge.vercel.app/")
   ```

5. Run the app on a device/emulator.

---

## 🛠 Folder Structure

```
.
├── MainActivity.kt        # WebView setup and JS bridge config
├── WebViewJavascriptBridge # JavaScript bridge library
├── assets/                # Injected JS
└── layout/                # UI layout files
```

---

## 🧪 Tips

* Ensure `JavaScript` is enabled in `WebViewSettings`
* Use HTTPS URLs for security and compatibility
* Always sanitize and validate messages between web and native

---

## 📝 License

MIT © [Sethy Rung](https://github.com/SethyRung)

---

## Contact

For any inquiries or suggestions, you can reach out to [Sethy Rung](https://github.com/SethyRung) via GitHub.
