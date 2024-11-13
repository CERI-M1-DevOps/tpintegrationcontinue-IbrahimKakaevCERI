package liste;

public class ListeSimple {
    private long size;
    Noeud tete;

/**
 * Returns the number of elements in the list.
 *
 * @return the size of the list
 */
    public long getSize() {
        return size;
    }

/**
 * Adds a new element to the beginning of the list.
 *
 * @param element the element to be added to the list
 */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Modifie la valeur du premier l  ment de la liste qui  gal   l'objet
     * <code>element</code> par <code>nouvelleValeur</code>.
     *
     * @param element l'objet   modifier
     * @param nouvelleValeur la nouvelle valeur   affecter   l'objet
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
     * Modifie la valeur de tous les l  ments de la liste qui sont   gaux   l'objet
     * <code>element</code> par <code>nouvelleValeur</code>.
     *
     * @param element l'objet   modifier
     * @param nouvelleValeur la nouvelle valeur   affecter   l'objet
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
     * Retourne une repr sentation textuelle de la liste.
     * Chaque l ment de la liste est repr sent  par un objet Noeud
     * qui est converti en une cha ne de caract res gr ce   sa
     * m thode toString().
     *
     * @return la repr sentation textuelle de la liste
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
     * Supprime le premier l  ment de la liste qui  gal  l'objet
     * <code>element</code>. Si l' l ment est trouv   la t te de la liste,
     * la t te est mise   jour pour pointer sur le suivant. Sinon, la liste
     * est parcourue jusqu'   ce que l' l ment soit trouv  et supprim .
     * R duit la taille de la liste de 1 si l' l ment est supprim .
     *
     * @param element l'objet   supprimer de la liste
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
     * Supprime tous les l  ments de la liste qui sont   gaux   l'objet
     * <code>element</code>. La liste est parcourue r cursive et chaque
     * l  ment   gal   l'objet est supprim , en mettant   jour la t te
     * de la liste ou en d calant le lien entre deux l  ments.
     * La taille de la liste est mise   jour en cons quence.
     *
     * @param element l'objet   supprimer de la liste
     */
    public void supprimeTous(Object element) {
        tete = supprimeTousRecurs(element, tete);
    }

    /**
     * M thode r cursive qui supprime tous les l  ments de la liste qui sont
     *   gaux   l'objet <code>element</code>. Les l  ments   supprimer
     * sont d tach s de la liste en mettant   jour les liens entre les
     * l  ments, ou en mettant   jour la t te de la liste si l' l ment   supprimer
     * est en t te de liste. La taille de la liste est mise   jour en
     * cons quence.
     *
     * @param element l'objet   supprimer de la liste
     * @param tete la t te de la liste   parcourir
     * @return la nouvelle t te de la liste apr s suppression
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
     * Renvoie l'avant-dernier l  ment de la liste. Si la liste est vide
     * ou ne contient qu'un l  ment, renvoie <code>null</code>.
     *
     * @return le dernier l  ment de la liste, ou <code>null</code>
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
     * Inverse l'ordre des l  ments de la liste. La t te de la liste
     * est mise   jour pour pointer sur le dernier l  ment de la liste
     * apr s inversion.
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
     * Renvoie le Noeud qui pr c de imm diatement le Noeud r dans la liste.
     * Si r est le premier l  ment de la liste, renvoie null.
     * @param r le Noeud dont on cherche le pr c dent
     * @return le pr c dent de r, ou null
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
     *  change deux Noeuds de la liste. Si les deux Noeuds sont diff rents, ils sont
     *  chang s dans la liste. Si r1 est la t te de la liste, r2 prend sa place.
     *  Si r2 est la t te de la liste, r1 prend sa place.
     *  @param r1 le premier Noeud   changer
     *  @param r2 le second Noeud   changer
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





