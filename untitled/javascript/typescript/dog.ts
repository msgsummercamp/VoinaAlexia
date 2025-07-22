type DogApiResponse = {
    message: string;
    status: string;
};

function showDog(): void {
    const btn = document.getElementById('dogBtn') as HTMLButtonElement | null;
    const img = document.getElementById('dogImg') as HTMLImageElement | null;

    if (!btn || !img) return;

    btn.disabled = true;
    btn.textContent = 'Loading...';

    fetch('https://dog.ceo/api/breeds/image/random')
        .then((res) => res.json())
        .then((data: DogApiResponse) => {
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

(window as any).showDog = showDog;
