package Main;
import Entity.Entity;

public class CollisionCheck {
    GamePanel gp;
    public CollisionCheck(GamePanel gp){
        this.gp = gp;
    }
    public void CheckTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.getTileSize();
        int entityRightCol = entityRightWorldX/gp.getTileSize();
        int entityTopRow = entityTopWorldY/gp.getTileSize();
        int entityBottomRow = entityBottomWorldY/gp.getTileSize();

        int tileNum1, tileNum2;

        switch(entity.direction){
            case("up"):
                entityTopRow = (entityTopWorldY - entity.speed)/gp.getTileSize();
                tileNum1 = gp.tileMan.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileMan.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileMan.tile[tileNum1].collision == true || gp.tileMan.tile[tileNum2].collision == true)
                    entity.collisionOn = true;
                break;
            case("down"):
                entityBottomRow = (entityBottomWorldY - entity.speed)/gp.getTileSize();
                tileNum1 = gp.tileMan.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileMan.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileMan.tile[tileNum1].collision == true || gp.tileMan.tile[tileNum2].collision == true)
                    entity.collisionOn = true;
                break;
            case ("left"):
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.getTileSize();
                tileNum1 = gp.tileMan.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileMan.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileMan.tile[tileNum1].collision == true || gp.tileMan.tile[tileNum2].collision == true)
                    entity.collisionOn = true;
                break;
            case("right"):
                entityRightCol = (entityRightWorldX - entity.speed)/gp.getTileSize();
                tileNum1 = gp.tileMan.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileMan.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileMan.tile[tileNum1].collision == true || gp.tileMan.tile[tileNum2].collision == true)
                    entity.collisionOn = true;
                break;

        }
    }
}
