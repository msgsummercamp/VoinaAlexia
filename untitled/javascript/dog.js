// dog.js
function showDog() {
    const btn = document.getElementById('dogBtn');
    const img = document.getElementById('dogImg');

    if (!btn || !img) return;

    btn.disabled = true;
    btn.textContent = 'Loading...';

    fetch('https://dog.ceo/api/breeds/image/random')
        .then((res) => res.json())
        .then((data) => {
            img.src = data.message;
            img.style.display = 'block';
        })
        .catch(() => {
            alert('Failed to fetch dog image.');
        })
        .finally(() => {
            btn.disabled = false;
            btn.textContent = 'Show me a dog!';
        });
}
