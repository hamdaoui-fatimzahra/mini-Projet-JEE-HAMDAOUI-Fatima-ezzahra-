 *   1- cette class contient l'ensemble des traitement de verifications a faire afin d'obtenir 
un fichier xlsx susceptible d'etre stocke dans la base de donnees 
 *   Verifications (successivement executes) :
             - de la colonne des notes de module
             - de la colonne de moyenne 
             - de la colonne validation (selon la session Normal / Rattrapage)
             - de nom d'enseignant est ce qu'il est compatible avec celle dans la base de donnees
             - l'annee est ce qu'elle est valid (avec l'annee courante (annee-precedente/annee-courante))
 *   2- tous les traitements de fonctionnalites sont implementes dans le controlleur "DBContrroller"(y inclus 
la verification de nom de l'enseignant ) 
 *   3- le package que j'avais ajoutte -com.boudaa.tools-qui contient les classes que j'avais implemente
        ainsi que "DBController" dans le package -com.gsnotes.web.utils.export- 
 *   
