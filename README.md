# javocab-api

English dictionary REST API built with [Quarkus](https://quarkus.io/), designed to run on a small self-hosted server and to be consumed by the InstinctVocab frontend.

## Requirements

- Java 25
- The dictionary data files (not included in this repository) in a single folder:

| File | Content |
|---|---|
| `english-dictionary.jsonl` | One JSON entry per word/part of speech (Wiktextract format) |
| `english-words.txt` | One word per line |
| `english-frequency.txt` | `word count` per line, most frequent first |

## Configuration

The data folder is set through the `JAVOCAB_SERVER_HOMOLOG` environment variable (trailing slash required). For local development, copy the example file and edit it:

```shell
cp .env.example .env
```

The application refuses to start if the variable is not set.

## Running

```shell
./mvnw quarkus:dev                         # dev mode with live reload, http://localhost:8080
./mvnw test                                # run tests (needs the data files)
./mvnw package                             # build target/quarkus-app/quarkus-run.jar
java -jar target/quarkus-app/quarkus-run.jar
```

## Endpoints

### `GET /dictionary/word-exists/{word}`

```shell
curl localhost:8080/dictionary/word-exists/example
```

```json
{"exists": true, "word": "example"}
```

Errors reading the data files return `500` with:

```json
{"error": "dictionary_unavailable", "message": "Dictionary is temporarily unavailable"}
```
