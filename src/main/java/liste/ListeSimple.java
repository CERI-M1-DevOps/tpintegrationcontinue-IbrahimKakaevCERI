package liste;

public class ListeSimple {
    private long size;
    Noeud tete;

    /**
     * Retourne le nombre d'éléments dans la liste.
     *
     * @return la taille de la liste
     */
    public long getSize() {
        return size;
    }

    /**
     * Ajoute un nouvel élément au début de la liste.
     *
     * @param element l'élément à ajouter à la liste
     */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Modifie la valeur du premier élément de la liste qui est égal à l'objet
     * <code>element</code> par <code>nouvelleValeur</code>.
     *
     * @param element l'objet à modifier
     * @param nouvelleValeur la nouvelle valeur à affecter à l'objet
     */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && !courant.getElement().equals(element)) {
            courant = courant.getSuivant();
        }
        if (courant != null) {
            courant.setElement(nouvelleValeur);
        }
    }

    /**
     * Modifie la valeur de tous les éléments de la liste qui sont égaux à l'objet
     * <code>element</code> par <code>nouvelleValeur</code>.
     *
     * @param element l'objet à modifier
     * @param nouvelleValeur la nouvelle valeur à affecter à l'objet
     */
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement().equals(element)) {
                courant.setElement(nouvelleValeur);
            }
            courant = courant.getSuivant();
        }
    }

    /**
     * Retourne une représentation textuelle de la liste.
     * Chaque élément de la liste est représenté par un objet Noeud
     * qui est converti en une chaîne de caractères grâce à sa
     * méthode toString().
     *
     * @return la représentation textuelle de la liste
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Supprime le premier élément de la liste qui est égal à l'objet
     * <code>element</code>. Si l'élément est trouvé à la tête de la liste,
     * la tête est mise à jour pour pointer sur le suivant. Sinon, la liste
     * est parcourue jusqu'à ce que l'élément soit trouvé et supprimé.
     * Réduit la taille de la liste de 1 si l'élément est supprimé.
     *
     * @param element l'objet à supprimer de la liste
     */
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement().equals(element)) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && !courant.getElement().equals(element)) {
                precedent = courant;
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /**
     * Supprime tous les éléments de la liste qui sont égaux à l'objet
     * <code>element</code>. La liste est parcourue récursivement et chaque
     * élément égal à l'objet est supprimé, en mettant à jour la tête
     * de la liste ou en décalant le lien entre deux éléments.
     * La taille de la liste est mise à jour en conséquence.
     *
     * @param element l'objet à supprimer de la liste
     */
    public void supprimeTous(Object element) {
        tete = supprimeTousRecurs(element, tete);
    }

    /**
     * Méthode récursive qui supprime tous les éléments de la liste qui sont
     * égaux à l'objet <code>element</code>. Les éléments à supprimer
     * sont détachés de la liste en mettant à jour les liens entre les
     * éléments, ou en mettant à jour la tête de la liste si l'élément à supprimer
     * est en tête de liste. La taille de la liste est mise à jour en
     * conséquence.
     *
     * @param element l'objet à supprimer de la liste
     * @param tete la tête de la liste à parcourir
     * @return la nouvelle tête de la liste après suppression
     */
    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement().equals(element)) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else {
            return null;
        }
    }

    /**
     * Renvoie l'avant-dernier élément de la liste. Si la liste est vide
     * ou ne contient qu'un élément, renvoie <code>null</code>.
     *
     * @return l'avant-dernier élément de la liste, ou <code>null</code>
     */
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null) {
            return null;
        } else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }

    /**
     * Inverse l'ordre des éléments de la liste. La tête de la liste
     * est mise à jour pour pointer sur le dernier élément de la liste
     * après inversion.
     */
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
     * Renvoie le Noeud qui précède immédiatement le Noeud donné dans la liste.
     * Si r est le premier élément de la liste, renvoie null.
     *
     * @param r le Noeud dont on cherche le précédent
     * @return le précédent de r, ou null
     */
    public Noeud getPrecedent(Noeud r) {
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }

    /**
     * Échange deux Noeuds de la liste. Si les deux Noeuds sont différents, ils sont
     * échangés dans la liste. Si r1 est la tête de la liste, r2 prend sa place.
     * Si r2 est la tête de la liste, r1 prend sa place.
     *
     * @param r1 le premier Noeud à échanger
     * @param r2 le second Noeud à échanger
     */
    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2)
            return;
        Noeud precedentR1, precedentR2;
        if (r1 != tete && r2 != tete) {
            precedentR1 = getPrecedent(r1);
            precedentR2 = getPrecedent(r2);
            precedentR1.setSuivant(r2);
            precedentR2.setSuivant(r1);
        } else if (r1 == tete) {
            precedentR2 = getPrecedent(r2);
            precedentR2.setSuivant(tete);
            tete = r2;
        } else if (r2 == tete) {
            precedentR1 = getPrecedent(r1);
            precedentR1.setSuivant(tete);
            tete = r1;
        }
        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }
}
