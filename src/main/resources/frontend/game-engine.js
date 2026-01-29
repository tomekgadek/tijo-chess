class ChessPiece {
    constructor(type, code) {
        this.type = type;
        this.code = code;
    }

    isCorrectMove(start, destination) {
        const figure = {
            start: start,
            destination: destination,
            type: this.type
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
}

class PiecePositioner {
    constructor(figureSelector) {
        this.figureSelector = figureSelector;
        this.figureId = figureSelector.replace('#', '');
    }

    hasFigure(field) {
        return Boolean(field.querySelector(this.figureSelector));
    }

    highlightSelected() {
        const figure = this.getFigure();
        if (figure) {
            figure.style.color = '#267340';
        }
    }

    resetSelected() {
        const figure = this.getFigure();
        if (figure) {
            figure.style.color = '#000000';
        }
    }

    removeSelected() {
        const figure = this.getFigure();
        if (figure) {
            figure.remove();
        }
    }

    placePiece(fieldId, pieceCode) {
        const field = document.getElementById(fieldId);
        if (field) {
            const span = document.createElement('span');
            span.id = this.figureId;
            span.innerHTML = pieceCode;
            field.appendChild(span);
        }
    }

    movePiece(fieldId, pieceCode) {
        this.resetSelected();
        this.removeSelected();
        this.placePiece(fieldId, pieceCode);
    }

    getFigure() {
        return document.querySelector(this.figureSelector);
    }
}

class ChessGame {
    constructor(rootSelector, piece, positioner) {
        this.root = document.querySelector(rootSelector);
        this.piece = piece;
        this.positioner = positioner;
        this.startPosition = null;
        this.destinationPosition = null;
        this.handleFieldMouseUp = this.handleFieldMouseUp.bind(this);
    }

    init() {
        this.buildChessboard(this.root);
        this.positioner.placePiece('c_1', this.piece.code);

        document.querySelectorAll('.field').forEach((field) => {
            field.addEventListener('mouseup', this.handleFieldMouseUp);
        });
    }

    handleFieldMouseUp(event) {
        const field = event.currentTarget;
        console.log('mousedown = ' + field.id);
        console.log('mousedown = ' + field.querySelector('#figure'));

        if (this.positioner.hasFigure(field) && this.startPosition === null) {
            this.startPosition = field.id;
            this.positioner.highlightSelected();
        } else if (this.startPosition !== null) {
            this.destinationPosition = field.id;

            this.piece.isCorrectMove(this.startPosition, this.destinationPosition)
                .then((response) => {
                    if (response) {
                        this.positioner.movePiece(this.destinationPosition, this.piece.code);
                        console.log("ruch jest poprawny!");
                    } else {
                        this.positioner.movePiece(this.startPosition, this.piece.code);
                        console.log("ruch jest niepoprawny!");
                        alert("Ruch jest niepoprawny!");
                    }

                    this.startPosition = null;
                    this.destinationPosition = null;
                });
        }
    }

    buildChessboard(chessboard) {
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
}

document.addEventListener('DOMContentLoaded', function() {
    const piece = new ChessPiece('BISHOP', '&#9815;');
    const positioner = new PiecePositioner('#figure');
    const game = new ChessGame('.chessboard', piece, positioner);
    game.init();
});
