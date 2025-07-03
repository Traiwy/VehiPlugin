package inv.main;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.Arrays;

public class MilestoneItems {
    private final JavaPlugin plugin;
    private final NamespacedKey customIdKey;
    public MilestoneItems(JavaPlugin plugin){
        this.plugin =plugin;
        this.customIdKey = new NamespacedKey(plugin,"custom_id");
    }

    public ItemStack createAcrobaticsItem() {
        ItemStack item = new ItemStack(Material.FEATHER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Акробатика");
        meta.setLore(Arrays.asList("§7Шанс уклонения от снарядов: 7%/15%/21%"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_acrobatics");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createSwiftTechniquesItem() {
        ItemStack item = new ItemStack(Material.SHIELD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Быстрые приемы");
        meta.setLore(Arrays.asList("§7Скорость с оружием: +10%/15%/20%"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_swift_techniques");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createVampirismItem() {
        ItemStack item = new ItemStack(Material.POTION);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Вампиризм");
        meta.setLore(Arrays.asList("§7Восстанавливает 3%/5%/7% здоровья от урона"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_vampirism");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createMasterAlchemistItem() {
        ItemStack item = new ItemStack(Material.BREWING_STAND);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Великий зельевар");
        meta.setLore(Arrays.asList("§7Продлевает баффы: +25%/50%/75%"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_master_alchemist");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createChiefProspectorItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Главный добытчик");
        meta.setLore(Arrays.asList("§7Шанс удвоить лут: 5%/10%/15%"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_chief_prospector");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createMobSlayerItem() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Убийца мобов");
        meta.setLore(Arrays.asList("§7Урон по мобам: +20%/40%/60%"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_mob_slayer");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createEnlightenmentItem() {
        ItemStack item = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Просветление");
        meta.setLore(Arrays.asList("§7Больше опыта с мобов: +7%/15%/21%"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_enlightenment");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createWarriorItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Воин");
        meta.setLore(Arrays.asList("§7Урон мечом: +3%/5%/7%"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_warrior");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createRegenerationItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Восстановление");
        meta.setLore(Arrays.asList("§7Восстанавливает 1 HP каждые 7/5/3 сек"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_regeneration");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createRunnerItem() {
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Гонец");
        meta.setLore(Arrays.asList("§7Скорость бега/ходьбы: +3%/5%/7%"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_runner");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createIronWillItem() {
        ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Железная воля");
        meta.setLore(Arrays.asList("§7Входящий урон -3%/5%/7% за 2 недостающих сердца"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_iron_will");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createMidasTouchItem() {
        ItemStack item = new ItemStack(Material.GOLD_INGOT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Рука Мидаса");
        meta.setLore(Arrays.asList("§7Золото с мобов: х2/х3/х4"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_midas_touch");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createInitiativeItem() {
        ItemStack item = new ItemStack(Material.CLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Инициатива");
        meta.setLore(Arrays.asList("§7При входе в PvP: +10/15/20% скорости, +0.05/0.07/0.1 атаки на 5 сек"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_initiative");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createColossusItem() {
        ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Колосс");
        meta.setLore(Arrays.asList("§7Максимальное здоровье: +6/8/10"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_colossus");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createIronSkinItem() {
        ItemStack item = new ItemStack(Material.LEATHER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Железная кожа");
        meta.setLore(Arrays.asList("§7Шипы 1/2/3 уровня независимо от брони"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_iron_skin");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createCrescendoItem() {
        ItemStack item = new ItemStack(Material.RED_DYE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Крещендо");
        meta.setLore(Arrays.asList("§7Исходящий урон +1%/1.5%/2% за каждое недостающее сердце"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_crescendo");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createPoisonerItem() {
        ItemStack item = new ItemStack(Material.SPIDER_EYE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Отравитель");
        meta.setLore(Arrays.asList("§7Длительность дебаффов: +15%/25%/35%"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_poisoner");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createDevourItem() {
        ItemStack item = new ItemStack(Material.ROTTEN_FLESH);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Пожирание");
        meta.setLore(Arrays.asList("§7Восстанавливает 50%/75%/100% здоровья от урона по мобам"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_devour");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createRevengeItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Месть");
        meta.setLore(Arrays.asList("§7Урон мечом +3/4/5% за каждое потерянное сердце за 15 сек"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_revenge");
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createMightItem() {
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Могущество");
        meta.setLore(Arrays.asList("§7Исходящий урон: +1%/2%/3%"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_might");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createImpatienceItem() {
        ItemStack item = new ItemStack(Material.SPLASH_POTION);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Нетерпеливость");
        meta.setLore(Arrays.asList("§7Ускоряет использование зелий/еды: +10%/20%/30%"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_impatience");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createRejectionItem() {
        ItemStack item = new ItemStack(Material.MILK_BUCKET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Отречение");
        meta.setLore(Arrays.asList("§7Уменьшает длительность дебаффов: -15%/30%/45%"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_rejection");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createRepulsionItem() {
        ItemStack item = new ItemStack(Material.SHIELD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Отторжение");
        meta.setLore(Arrays.asList("§7Шанс не получить урон из любого источника: 3%/6%/9%"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_repulsion");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createFirstStrikeItem() {
        ItemStack item = new ItemStack(Material.DIAMOND);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Первый ход");
        meta.setLore(Arrays.asList("§7Первый удар в PvP: х1.5/1.7/2 урона"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_first_strike");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createFullReachItem() {
        ItemStack item = new ItemStack(Material.FISHING_ROD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Полный охват");
        meta.setLore(Arrays.asList("§7Дальность атаки: +0.3/0.5/0.7 блока"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_full_reach");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createSwimmerItem() {
        ItemStack item = new ItemStack(Material.HEART_OF_THE_SEA);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Плавец");
        meta.setLore(Arrays.asList("§7Скорость в воде: +10%/20%/30%"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_swimmer");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createAgilityItem() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Проворность");
        meta.setLore(Arrays.asList("§7Скорость атаки мечом: +0.07/0.1/0.12"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_agility");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createFerocityItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Свирепость");
        meta.setLore(Arrays.asList("§7Скорость атаки топором: +0.1/0.12/0.15"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_ferocity");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createStrongmanItem() {
        ItemStack item = new ItemStack(Material.IRON_AXE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Силач");
        meta.setLore(Arrays.asList("§7Урон топором: +3%/5%/7%"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_strongman");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createSpeedOfThoughtItem() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Скорость мысли");
        meta.setLore(Arrays.asList("§7Скорость натягивания лука/арбалета/трезубца: +5%/10%/15%"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_speed_of_thought");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createSteelMusclesItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Стальные мышцы");
        meta.setLore(Arrays.asList("§7Броня: +4/6/8 единиц"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_steel_muscles");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createGlassWeaponItem() {
        ItemStack item = new ItemStack(Material.GLASS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Стеклянное оружие");
        meta.setLore(Arrays.asList("§7Исходящий урон +10%/15%/20%, входящий +15%/20%/25%"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_glass_weapon");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createPrecisionItem() {
        ItemStack item = new ItemStack(Material.CROSSBOW);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Точность");
        meta.setLore(Arrays.asList("§7Каждая 5/4/3 атака — крит"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_precision");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createHeavyArmorItem() {
        ItemStack item = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Тяжелая броня");
        meta.setLore(Arrays.asList("§7Броня +5/6/7 (алмаз), +6/7/8 (незерит) при полном комплекте"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_heavy_armor");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createDodgeItem() {
        ItemStack item = new ItemStack(Material.FEATHER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Уклонение");
        meta.setLore(Arrays.asList("§7Шанс не получить урон от меча/топора: 5%/7%/10%"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_dodge");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createEliminationItem() {
        ItemStack item = new ItemStack(Material.FLINT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Устранение");
        meta.setLore(Arrays.asList("§7Урон +1%/1.5%/2% за последовательные удары (до 10%/15%/20%)"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_elimination");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createChampionItem() {
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Чемпион");
        meta.setLore(Arrays.asList("§7Броня +1/2/3, здоровье +2/4/6"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_champion");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createPureLuckItem() {
        ItemStack item = new ItemStack(Material.EMERALD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Чистая удача");
        meta.setLore(Arrays.asList("§7Шанс крита без прыжка: 3%/6%/9%"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_pure_luck");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createCheatingItem() {
        ItemStack item = new ItemStack(Material.BONE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Шулерство");
        meta.setLore(Arrays.asList("§7Шанс не потратить расходуемый предмет: 3%/6%/9%"));
        meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.STRING, "milestone_cheating");
        item.setItemMeta(meta);
        return item;
    }
}