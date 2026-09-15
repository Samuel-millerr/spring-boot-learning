DROP TABLE IF EXISTS "books";
DROP TABLE IF EXISTS "authors";

DROP SEQUENCE IF EXISTS authors_id_seq;
CREATE SEQUENCE authors_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE "authors"
(
    "id"   BIGINT DEFAULT NEXTVAL('authors_id_seq') NOT NULL,
    "name" VARCHAR(255)                            NOT NULL,
    "age"  INT                                     NOT NULL,
    CONSTRAINT "authors_pkey" PRIMARY KEY ("id")
);

CREATE TABLE "books"
(
    "isbn"     TEXT   NOT NULL,
    "title"    TEXT   NOT NULL,
    "author_id" BIGINT NOT NULL,
    CONSTRAINT "books_pkey" PRIMARY KEY ("isbn"),
    CONSTRAINT "fk_author" FOREIGN KEY (author_id)
        REFERENCES authors (id)
);