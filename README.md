# Forward-Dependent-Bureaucracy[![Build Status](https://travis-ci.org/ozanonurtek/Forward-Dependent-Bureaucracy.svg?branch=master)](https://travis-ci.org/ozanonurtek/Forward-Dependent-Bureaucracy)
Linovi 2016 | Forward Dependent Bureaucracy Project

## A Brief Explanation:

- This project simply sorts the jobs via using [dependency graph](https://en.wikipedia.org/wiki/Dependency_graph) logic. It also creates an  [adjacency matrix](https://en.wikipedia.org/wiki/Adjacency_matrix) to show connections between jobs.

## What I Researched:

- [Graph Theory] (https://en.wikipedia.org/wiki/Graph_theory)
- [Depth-Breadth First Search] (https://www.ics.uci.edu/~eppstein/161/960215.html)
- [Tree Traversal] (http://www.tutorialspoint.com/data_structures_algorithms/tree_traversal.htm)
- [Package Manager] (https://en.wikipedia.org/wiki/Package_manager)
- [Topological Sort] (https://www.cs.usfca.edu/~galles/visualization/TopoSortDFS.html)

## What is inside:

#### src:

- This folder contains 4 java file, 
- 2 of them test the project(Test.java is for GUI and TestConsole.java is for testing from console.).
- 1 of them reads json file and prepares fields for algorithm(JsonOp.java).
- 1 of them prepares adjacency matrix according to connection and solves the graph(RepresentAsGraph.java).

#### json-test:

- This folder contains 4 json file to test the project, 
- 1 of them has impossible (circular) connection. If you test it(depent_on_each_other.json) you can see that program throws "What the hell exception". :grinning:
- The rest is just for test.

#### json-simple-1.1.1.jar:

- 3rd Party Library to read json from json file format.

#### .travis.yml:

- To show that build is passing and project is running on Travis-CI

#### build.xml:

- Xml script to Ant build of the project.

## Test and Contact:
##### You can download the latest stabil release of project to test it via using a simple GUI.
##### You can send me an email to ask your question, please feel free to contribute.
- Ozan Onur Tek : *ozanonurtek@gmail.com*

## Updates

- In branch "optimized-version" you can find the latest optimized changes about project. 
- The order of the jobs in mConnection arraylist has been changed in JsopOp.java file. Thus in RepresentAsGraph.java no need to reverse the mResult list anymore.
- Reversing a list is not a big deal but it was a redundant piece of code.
