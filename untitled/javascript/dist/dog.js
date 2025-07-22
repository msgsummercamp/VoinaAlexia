"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
const heading = document.createElement('h1');
heading.textContent = 'Random Dog Gallery';
document.body.appendChild(heading);
const btn = document.createElement('button');
btn.id = 'dogBtn';
btn.textContent = 'Show me a dog!';
document.body.appendChild(btn);
const img = document.createElement('img');
img.id = 'dogImg';
img.style.display = 'none';
img.style.maxWidth = '400px';
img.style.marginTop = '20px';
img.style.borderRadius = '8px';
document.body.appendChild(img);
// @ts-ignore
btn.addEventListener('click', () => __awaiter(void 0, void 0, void 0, function* () {
    btn.disabled = true;
    btn.textContent = 'Loading...';
    try {
        const res = yield fetch('https://dog.ceo/api/breeds/image/random');
        const data = yield res.json();
        img.src = data.message;
        img.style.display = 'block';
    }
    catch (e) {
        alert('Failed to fetch dog image.');
    }
    finally {
        btn.disabled = false;
        btn.textContent = 'Show me a dog!';
    }
}));
