function isCorrectMove(start, destination, type) {
    var figure = {
        start: start,
        destination: destination,
        type: type
    };

    return fetch('http://127.0.0.1:8080/api/chess/is-correct-move', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(figure)
    })
        .then(function (response) {
            if (!response.ok) {
                console.log("error...");
                alert("Wystąpił nieoczekiwany problem z usługą!");
                return false;
            }
            console.log('success...');
            return response.json();
        })
        .catch(function (error) {
            console.error("Wystąpił błąd:", error);
            alert("Wystąpił nieoczekiwany problem z usługą!");
            return false;
        });
}

document.addEventListener('DOMContentLoaded', function () {
    var startPosition = null;
    var destinationPosition = null;

    document.querySelectorAll('.field').forEach(function (field) {
        field.addEventListener('mouseup', function () {
            console.log('mousedown = ' + this.id);
            console.log('mousedown = ' + this.querySelector('#figure'));

            if (this.querySelector('#figure') && startPosition === null) {
                startPosition = this.id;
                document.querySelector('#figure').style.color = '#267340';
            } else if (startPosition !== null) {
                destinationPosition = this.id;

                isCorrectMove(startPosition, destinationPosition, 'BISHOP')
                    .then(function (response) {
                        if (response) {
                            document.querySelector('#figure').style.color = '#000000';
                            document.querySelector('#figure').remove();
                            document.getElementById(destinationPosition).innerHTML = '<span id="figure">&#9815;</span>';
                        } else {
                            document.querySelector('#figure').style.color = '#000000';
                            document.querySelector('#figure').remove();
                            document.getElementById(startPosition).innerHTML = '<span id="figure">&#9815;</span>';
                        }

                        startPosition = null;
                        destinationPosition = null;
                    });
            }
        });
    });
});
