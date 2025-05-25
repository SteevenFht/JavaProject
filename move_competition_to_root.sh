#!/bin/bash

# Vérifie que le dossier competition existe
if [ ! -d "competition" ]; then
  echo "Le dossier 'competition' n'existe pas dans le répertoire courant."
  exit 1
fi

echo "Déplacement des fichiers de 'competition/' vers la racine..."

# Déplace tout le contenu (y compris fichiers cachés) de competition vers la racine
shopt -s dotglob  # inclure fichiers cachés
mv competition/* .
shopt -u dotglob

# Supprime le dossier competition vide
rmdir competition

echo "Déplacement terminé !"

# Affiche l'arborescence pour vérifier
tree -L 2
