function isCorrectMove(start, destination, type) {
    const figure = {
        start: start,
        destination: destination,
        type: type
    };

    return fetch('/api/chess/is-correct-move', {
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

document.addEventListener('DOMContentLoaded', function() {
    let startPosition = null;
    let destinationPosition = null;

    buildChessboard(document.querySelector('.chessboard'));
    addPiece('c_1', '&#9815;');

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
                            console.log("ruch jest poprawny!");
                        } else {
                            document.querySelector('#figure').style.color = '#000000';
                            document.querySelector('#figure').remove();
                            document.getElementById(startPosition).innerHTML = '<span id="figure">&#9815;</span>';
                            console.log("ruch jest niepoprawny!");
                            alert("Ruch jest niepoprawny!");
                        }

                        startPosition = null;
                        destinationPosition = null;
                    });
            }
        });
    });
});

function buildChessboard(chessboard) {

    function createHeader(text) {
        const div = document.createElement('div');
        div.classList.add('field', 'header');
        div.textContent = text;
        return div;
    }

    chessboard.appendChild(createHeader(''));
    ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'].forEach(letter => {
        chessboard.appendChild(createHeader(letter));
    });
    chessboard.appendChild(createHeader(''));

    // Wiersze planszy od 8 do 1
    for (let row = 8; row >= 1; row--) {
        chessboard.appendChild(createHeader(row));

        ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'].forEach((col, index) => {
            // Określ kolor: parzysta suma (row + index) -> white, nieparzysta -> black
            const color = (row + index) % 2 === 0 ? 'white' : 'black';
            const field = document.createElement('div');
            field.classList.add('field', color);
            field.id = `${col}_${row}`;
            chessboard.appendChild(field);
        });

        chessboard.appendChild(createHeader(row));
    }

    chessboard.appendChild(createHeader(''));
    ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'].forEach(letter => {
        chessboard.appendChild(createHeader(letter));
    });

    chessboard.appendChild(createHeader(''));
}

function addPiece(fieldId, pieceCode) {
    const field = document.getElementById(fieldId);
    if (field) {
        const span = document.createElement('span');
        span.id = 'figure';
        span.innerHTML = pieceCode;
        field.appendChild(span);
    }
}
