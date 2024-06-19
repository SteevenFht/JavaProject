main: jeu

cls:
	javac -classpath "lib/json-20211205.jar" -sourcepath src -d classes src/*.java

winExec: cls
	java -classpath "classes;lib/json-20211205.jar" Main

linExec: cls
	java -classpath "classes:lib/json-20211205.jar" Main

windebug: cls
	java -classpath "classes;lib/json-20211205.jar" Main -d

lindebug: cls
	java -classpath "classes:lib/json-20211205.jar" Main -d

jar: cls
	$ (cd classes && jar cvfm ../lib/jeu.jar ../src/META-INF/MANIFEST.MF *.class -C . display -C . entity -C . plateau)

jeu: jar
	java -jar ./lib/jeu.jar

debug: jar
	java -jar ./lib/jeu.jar -d

doc:
	javadoc -classpath "./lib/json-20211205.jar" -sourcepath "./src" -d docs src/*.java entity entity.character entity.item entity.util display plateau plateau.utilities

clean:
	rm -r classes docs lib/jeu.jar