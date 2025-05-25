sourcepath=src
docsDestination=docs
classpath=classes
package=sportsCompetition
subPackage = sportsCompetition.competition sportsCompetition.competitorSelection sportsCompetition.exception sportsCompetition.match sportsCompetition.util sportsCompetition.observer
main=Main
jarName=appli
jarDirectory=.
pathClassesForTest = ./src/sportsCompetition/*.java ./src/sportsCompetition/util/*.java ./src/sportsCompetition/competition/*.java ./src/sportsCompetition/competitorSelection/*.java ./src/sportsCompetition/exception/*.java ./src/sportsCompetition/match/*.java ./src/sportsCompetition/observer/*.java
pathTestForTest = ./test/sportsCompetitionTest/*.java ./test/sportsCompetitionTest/mock/*.java ./test/sportsCompetitionTest/competition/*.java ./test/sportsCompetitionTest/competitorSelection/*.java
testClasspath= $(pathClassesForTest) $(pathTestForTest)
junitConsolePath=./lib/junit-platform-console-standalone-1.9.0.jar

testDetailMode=tree
# modes available: none, summary, flat, tree, verbose
# default=tree
.PHONY: test

all: docs class jar

docs:
	javadoc -classpath $(sourcepath) -d $(docsDestination) $(package) $(subPackage)

class:
	javac -classpath $(sourcepath) -d $(classpath) $(sourcepath)/$(package)/*.java

jar: class
	jar cvfe $(jarDirectory)/$(jarName).jar $(package).$(main) -C $(classpath) .

testCompilation:
	javac -d $(classpath) -classpath $(junitConsolePath) $(testClasspath)

test: testCompilation
	java -jar $(junitConsolePath) -cp $(classpath) --scan-classpath --disable-banner --details=$(testDetailMode)

launch: class
	 java -classpath $(classpath) $(package).$(main)

launchJAR: jar
	java -jar $(jarDirectory)/$(jarName).jar

clean:
	rm -r classes docs $(jarDirectory)/$(jarName).jar
