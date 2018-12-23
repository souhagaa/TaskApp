package core;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class TacheLinkedList implements TacheList {
    List<Tache> tacheList;

    public TacheLinkedList() {
        tacheList = new LinkedList<>();
    }
    public TacheLinkedList(List<Tache> list) {
        tacheList = new LinkedList<>(list);
    }
    @Override
    public void ajouterTache(Tache tache) {
        tacheList.add(tache);
    }

    @Override
    public boolean supprimerTache(long id) {
        Tache tache = rechercherTache(id);
        return tacheList.remove(tache);
    }
    @Override
    public boolean supprimerTache(Tache tache) {
        return tacheList.remove(tache);
    }
    @Override
    public Tache rechercherTache(long id) {
        return tacheList.stream().filter(tache -> tache.getId()==id).findFirst().get();
    }

    @Override
    public void modifierTache(long id,String toModify,String newValue) {
        Tache tache = rechercherTache(id);
        switch (toModify){
            case "titre":
                tache.setTitre(newValue);
                break;
            case "description":
                tache.setDescription(new StringBuffer(newValue));
                break;
            case "beforeTime":
                tache.setBeforeTime(LocalDateTime.now());
                break;
            case "status":
                tache.setStatus(Integer.parseInt(newValue));
                break;
        }
    }

    @Override
    public TacheList tacheSaye() {
        return new TacheLinkedList(tacheList.stream().filter(tache -> tache.getStatus()==0).collect(Collectors.toList()));
    }

    @Override
    public TacheList tacheMazel() {

        return new TacheLinkedList(tacheList.stream()
                .filter(tache -> (tache.getStatus()==1))
                .collect(Collectors.toList()));
    }

    @Override
    public TacheList tacheTimeOut() {
        return new TacheLinkedList(tacheList.stream()
                .filter(tache ->((tache.getStatus()==1) && (tache.getBeforeTime().isBefore(LocalDateTime.now()))))
                .collect(Collectors.toList()));
    }

    @Override
    public List<Tache> getTacheList() {
        return this.tacheList;
    }

    @Override
    public String toString() {
        return "TacheLinkedList{" +
                "tacheList=" + tacheList +
                '}';
    }
}
