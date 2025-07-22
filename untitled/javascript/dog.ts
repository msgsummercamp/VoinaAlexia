type DogApiResponse = {
    message: string;
    status: string;
};

const heading: HTMLHeadingElement = document.createElement('h1');
heading.textContent = 'Random Dog Gallery';
document.body.appendChild(heading);

const btn: HTMLButtonElement = document.createElement('button');
btn.id = 'dogBtn';
btn.textContent = 'Show me a dog!';
document.body.appendChild(btn);

const img: HTMLImageElement = document.createElement('img');
img.id = 'dogImg';
img.style.display = 'none';
img.style.maxWidth = '400px';
img.style.marginTop = '20px';
img.style.borderRadius = '8px';
document.body.appendChild(img);

// @ts-ignore
btn.addEventListener('click', async (): Promise<void> => {
    btn.disabled = true;
    btn.textContent = 'Loading...';

    try {
        const res: Response = await fetch('https://dog.ceo/api/breeds/image/random');
        const data: DogApiResponse = await res.json();

        img.src = data.message;
        img.style.display = 'block';
    } catch (e) {
        alert('Failed to fetch dog image.');
    } finally {
        btn.disabled = false;
        btn.textContent = 'Show me a dog!';
    }
});
