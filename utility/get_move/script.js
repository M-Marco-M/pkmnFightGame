//Mapping DB's id and name
const mappaIdTipi = new Map([
  ["normal", 1],
  ["fire", 2],
  ["water", 3],
  ["electric", 4],
  ["grass", 5],
  ["ice", 6],
  ["fighting", 7],
  ["poison", 8],
  ["ground", 9],
  ["flying", 10],
  ["psychic", 11],
  ["bug", 12],
  ["rock", 13],
  ["ghost", 14],
  ["dragon", 15],
  ["dark", 16],
  ["steel", 17],
  ["fairy", 18],
]);

//This function, change the string for the type with a number basing on the previous map
const setTypeId = ({ name, url }, mappaTipi) => mappaTipi.get(name);

///////////////////////////////////////////////////////////////////////////

//Select all moves from first three generations
const getMovesFromGenerations = async function (generations) {
  const movesArray = [];
  for (gen of generations) {
    const URL = `https://pokeapi.co/api/v2/generation/${gen}`;

    const response = await fetch(URL);
    const data = await response.json();

    movesArray.push(await data.moves);
  }

  const allMoves = movesArray.flat();
  return allMoves;
};

const generations = [1, 2, 3];

const allMovesRef = getMovesFromGenerations(generations);
console.log(allMovesRef);

getMovesFromGenerations(generations);

//Get the needed info for every move and basically return an array of object that represents move
const getMovesInfo = async function (moves) {
  const arr = [];
  for (move of await moves) {
    const response = await fetch(move.url);
    const data = await response.json();
    arr.push({
      id: data.id,
      name: data.name,
      accuracy: data.accuracy,
      pp: data.pp,
      power: data.power,
      type: data.type,
      target: data.target,
      learnedBy: data.learned_by_pokemon,
    });
  }
  return arr;
};

const movesInfo = getMovesInfo(allMovesRef);
console.log(getMovesInfo(allMovesRef));

//Convert the objects in array for a simpler manipulation
const getMovesArrayFormat = async function (moves) {
  const arr = [];
  for (move of await moves) {
    arr.push([
      move.id,
      move.name,
      move.accuracy,
      move.pp,
      move.power,
      setTypeId(move.type, mappaIdTipi),
      move.target.name,
    ]);
  }
  return arr;
};

console.log(getMovesArrayFormat(movesInfo));

//Create the query to insert moves in the table move basing on the array of moves
const createMoveInsertQuery = async function (moves) {
  const mosse = await moves;
  return mosse.reduce(
    (string, [id, name, accuracy, pp, power, type, target], i) =>
      string +
      `(${id}, "${name}", ${accuracy === null ? 0 : accuracy}, ${pp}, ${
        power === null ? 0 : power
      }, ${type}, "${target}")${i < mosse.length - 1 ? ",\n" : ";"}`,
    "INSERT INTO `move` (`move_id`, `name`, `accuracy`, `pp`,`power`,`type_id`, `target`) VALUES\n"
  );
};

console.log(createMoveInsertQuery(getMovesArrayFormat(movesInfo)));

//Get an array of pokedex number from first three generation which can learn that move (selected by the id)
const getMoveLearnedByPokedexNumberArray = async function (moves) {
  const mosse = await moves;
  return mosse.map((move) => {
    const pkmnId = move.learnedBy
      .map(({ name, url }) => Number(url.split("/").at(-2)))
      .filter((pokedexNumber) => pokedexNumber <= 386);
    return [move.id, pkmnId];
  });
};

console.log(getMoveLearnedByPokedexNumberArray(movesInfo));

//Get an array of couple of number: the pokedex number which can learn a move and the move id
const getMoveLearnedByPokedexNumber = async function (movesIdAndPkmnId) {
  const arr = await movesIdAndPkmnId;
  return arr
    .map(([moveId, pkmnId]) => pkmnId.map((pkmnId) => [moveId, pkmnId]))
    .flat();
};

const pkmnAndMove = getMoveLearnedByPokedexNumber(
  getMoveLearnedByPokedexNumberArray(movesInfo)
);
console.log(pkmnAndMove);

//Create the query to populate the table movepool with the couple of pokedexNumber and the moveId
const createQueryInsertMovepool = async function (pkmnAndMove) {
  const arr = await pkmnAndMove;
  return arr.reduce(
    (string, [moveId, pkmnId], i) =>
      string + `(${moveId}, ${pkmnId})${i < arr.length - 1 ? ",\n" : ";"}`,
    "INSERT INTO `movepool` (`move_id`, `pokedex_number`)\n"
  );
};

console.log(createQueryInsertMovepool(pkmnAndMove));
