
document.getElementById("reloadButton").addEventListener("click", function() {
    fetch('/reload', {
        method: 'POST'
    }).then(response => {
        if (response.ok) {
            console.log('Reload signal sent successfully.');
        } else {
            console.error('Error sending reload signal.');
        }
    }).catch(error => {
        console.error('Error:', error);
    });
});

