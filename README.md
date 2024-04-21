# Colt Express

> Finir est souvent bien plus difficile que commencer.
— Jack Beauregard

![demo](demo.gif)

## Sommaire

- [Introduction](#introduction)
- [Fonctionnalités](#fonctionnalités)
- [Difficultés](#difficultes)
- [Diagramme UML](#diagramme-uml)
- [Utilisation](#utilisation)

## Introduction
Projet de POGL pour l'année 2023-2024 sur le jeu de société [Colt Express](https://www.ludonaute.fr/en/product/colt-express/)
réalisé par:
- Aymeric Emond <<aymeric.emond@universite-paris-saclay.fr>>
- Tristan Hartmann-Baudry  <<tristan.hartmann-baudry@universite-paris-saclay.fr>>


## Fonctionnalités
### Wagons
Chaque wagon possède deux étages (intérieur et toit). Tout deux sont accessibles
aux bandits et trésors. Le premier wagon, c'est-à-dire la locomotive, possède
une illustration différente.  
Chaque étage est représenté par la classe `Toigon`
qui possède une table de ses voisins accessibles en fonction de la direction relative
à lui-même. Ils mémorisent également la liste des entitées présentes sur/dans le toigon
en fonction de leur type.

### Entites
Les bandits, shériff(s) et trésors héritent tous de la classe `Entite` qui se charge
de la position (le toigon) et des déplacements.  
Pour pouvoir différencier les différentes entités notamment dans une table `HashMap`,
chaque sous-classe précise un type (`Entite.Type.BANDIT`, `Entite.Type.SHERIFF`, `Entite.Type.TRESOR`).  

### Bandits
Un bandit est le personnage d'un joueur. Le nom et l'illustration des bandits est
défini aléatoirement au lancement du jeu en fonction du contenu de `src/resources/images/bandits/`.
On peut donc, sans modifier le code source, ajouter de nouveaux bandits en ajoutant une
nouvelle illustration.  
Chaque bandit a un nombre de balles et mémorise dans une liste les trésors qu'il a ramassé
ainsi que les actions qu'il a choisie.    
Le nombre de bandits/joueurs est défini par la constante `Modele.NB_BANDITS`.

### Sheriffs
Dans le jeu de société d'origine, il n'y a qu'un seul sheriff, cependant, le code
source a été fait de manière à supporter un nombre quelconque de sheriffs dans le train.
Ce nombre est défini par la constante `Modele.NB_SHERIFFS`.  
Le shériff (ou Marshall dans le sujet du projet) se déplace aléatoirement après chaque
action des bandits avec une probabilité défini par `Modele.NERVOSITE_SHERIFF`
et dans une direction aléatoire (`ARRIERE` ou `AVANT`), avec l'exception des extrémités
du train ou la direction sera forcée.

### Trésors
Il y a trois types de trésors: Bourse, Bijoux et Magot. Chacun possède une illustration
unique.

### Actions
Toutes les actions héritent de la classe abstraite `Action` pour pouvoir
être ajouté à une liste commune dans `Bandit`. Elles implémentent trois
méthodes abstraites: `getIcon()`, `apply(Bandit b)` et `canApply(Bandit b)`.
#### Déplacement
Le bandit change de `Toigon` dans une direction choisie. L'action sera nullifié
durant la phase de jeu si elle n'est pas possible (i.e sortir du train ou aller
dans le wagon du shériff).
#### Braquage
Le bandit peut braquer des passages (les passagers ne sont pas représentés visuellement)
pour obtenir un trésor (Bijou, Bourse ou Magot). L'action sera également nullifié s'il
n'y a aucun trésor présent dans le toigon du bandit.
#### Tirer
Un bandit peut tirer à l'aide de son révolver dans une des quatre directions existantes.
S'il touche un autre bandit, celui-ci devra lâcher un de ses trésors.
Dans le cas ou le bandit n'a pas de trésor, ou qu'il n'y a pas de bandit dans ce toigon,
l'action sera juste ignorée.

### Phases
Il y a deux phases lors d'une partie: `Phase.CHOIX` et `Phase.JEU`.
Durant la première, les bandits vont choisir chacun leur tour les 3 actions
qu'ils souhaitent réaliser (Ils peuvent en ajouter et en retirer tant qu'ils
n'ont pas confirmé leur choix).
Puis, dans la deuxième phase, la 1re action des bandits sera effectué l'une après
l'autre, puis la 2eme etc...    
Lors de cette deuxième phase, le joueur a à sa disposition
un bouton qui permet d'effectuer les actions une par une pour plus facilement
suivre le déroulement du jeu.

### Interface / Visuels
Tous les boutons possèdent une illustration personnalisée. L'interface n'est pas
très flexible, donc la taille de la fenêtre est fixée.
Toutes les illustrations ont été réalisées par nous-même à l'exception
du titre du jeu (Colt Express).  
Comme visible dans la démo, le train (ou plutôt l'arrière-plan) se déplace
en continu en boucle.

### Musiques et Bruit Ambiant
Le jeu joue en continu une bande son composée de deux musiques séparées
d'un bruit ambiant de desert. En plus de cette bande son, il y a un bruit de rail
qui est joué en parallèle.

## Difficultés
La principale difficulté que nous avons rencontrée était de se coordonner
efficacement sur les différentes tâches et objectifs à atteindre, donc d'organisation.
C'est-à-dire que nous avons tous deux écrit plus ou moins le même code en parallèle sans
en avoir conscience, et évidemment à terme, un seul n'est gardé, ce qui représente
une perte de temps.  

Il y avait également des problèmes de performance durant le développement, mais
en déboguant, on a pu identifier l'origine du problème (qui était un "bug" plutôt
qu'une mauvaise architecture/code). Depuis le jeu tourne plus ou moins correctement.

Enfin, mettre en œuvre l'interface qu'on voulait fut assez fastidieux car les
images étaient difficiles à placer correctement et se base sur quelques nombres "magiques"
trouver "à la pipette" en testant plusieurs fois.

## Diagramme UML
![diagram](diagram.png)

## Utilisation
Pour jouer au jeu, vous pouvez soit compiler les sources du projet par
vous même, ou alors lancer avec java le fichier [ColtExpress.jar](ColtExpress.jar).