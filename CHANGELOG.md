# Changelog

## 1.3.10

- Fix EMI crash during startup (`MixinTargetAlreadyLoadedException` on `EmiPlugin`) by not class-loading mod classes in the mixin plugin
- Chemical Plant: clamp catalyst damage chance to [0, 100], fix appearance NPE for unknown casing tiers, display parallel from `maxParallel`
