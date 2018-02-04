# Compilador de C para Assembly.

# Gerar Analisador Lexico

```sh
$ java -jar libs/jflex.jar --noinputstreamctor -d ./java/src/compiler/generated ./spec/Scanner.jflex
```

# Gerar Analisador semantico

```sh
$ java -jar libs/cup.jar -compact_red -expect 10000 -package compiler.generated -destdir ./java/src/compiler/generated -parser Parser ./spec/Parser.cup
```
