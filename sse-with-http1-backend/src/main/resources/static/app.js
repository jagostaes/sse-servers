const eventSource = new EventSource('sse-server/chuck-norris-joke-stream')

eventSource.onmessage = function (e) {
    const joke = JSON.parse(e.data);
    showJoke(joke.value);
}

function showJoke(joke) {
    var table = document.getElementById("jokes");
    var row = table.insertRow(-1);
    var cell = row.insertCell(0);
    cell.innerHTML = joke;
}
