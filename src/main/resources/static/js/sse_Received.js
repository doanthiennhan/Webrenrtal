
const eventSource = new EventSource('/sse');
eventSource.addEventListener('message', function (event) {
    console.log('Received message: ' + event.data);
    if (event.data === 'reload') {
        console.log('Reloading the page...');
        location.reload();
    }
});
