window.onload = function () {
    const button = document.getElementById('next');
    button.disabled = true;

    document.querySelectorAll('.custom-control-input').forEach(item => {
        item.addEventListener('click', event => {
            button.disabled = false;
        })
    })
}
